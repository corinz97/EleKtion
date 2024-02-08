package entities.abstract

import com.benasher44.uuid.uuid4
import entities.implementations.DescendingListOfPreferencesVote
import entities.interfaces.Competition
import entities.interfaces.Competitor
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.Poll
import entities.interfaces.PollAlgorithm
import entities.interfaces.Ranking
import entities.interfaces.SinglePreferenceVote
import entities.interfaces.Vote
import entities.interfaces.Voter
import entities.interfaces.dsls.CompetitionDSL
import entities.interfaces.dsls.PollDSL
import entities.types.ScoreMetric

/**
 *
 */
abstract class PollAbstraction<S : ScoreMetric, V : Vote> :
    Poll<S, V>,
    PollDSL<S, V> {
    override lateinit var pollAlgorithm: PollAlgorithm<S, V>
    override lateinit var competition: Competition<S>
    override lateinit var votesList: List<V>

    override fun computePoll(): Ranking<S> = pollAlgorithm.computeByAlgorithmRules(votesList)

    override fun List<String>.asAnonymousVote(): ListOfPreferencesVote<S> {
        if (this.isEmpty()) error("Votes list cannot be empty")
        var thisList = this
        val setOfCompetitors = thisList.toSet()
        val candidates = this@PollAbstraction.competition.competitors.map { it.name }.toSet()

        if (setOfCompetitors != candidates) { // mismatch between sets
            if ((setOfCompetitors - candidates).isNotEmpty()) {
                error("A list of preferences contains one o more not allowed candidate")
            }
            if ((candidates - setOfCompetitors).isNotEmpty()) {
                // every candidate must be present in the list of competitors
                // error("Every allowed candidate must be present in every list of preferences")
                // add missing competitors, will be put at the end for the list
                thisList = thisList + (candidates - setOfCompetitors)
            }
        }
        val groupCount = thisList.groupingBy { it }.eachCount()
        // every candidate can be present only once in the list of competitors
        if (groupCount.any { count -> count.value > 1 }) {
            error("Every allowed candidate can be present only once in the list of competitors")
        }

        val listOfCompetitorObject = mutableListOf<Competitor<S>>()
        thisList.forEach { actualName ->

            listOfCompetitorObject +=
                this@PollAbstraction.competition.competitors.firstOrNull { comp ->
                    comp.name == actualName
                }.let {
                    it ?: throw NoSuchElementException("Voted candidate doesn't exist as object")
                }
        }

        return DescendingListOfPreferencesVote<S>().apply {
            voter =
                object : Voter {
                    override val identifier: String
                        get() = uuid4().toString()
                }
            votedCompetitors = listOfCompetitorObject
        }
    }

    override fun String.asAnonymousVote(): SinglePreferenceVote<S> {
        val comp =
            this@PollAbstraction.competition.competitors.firstOrNull {
                it.name == this@asAnonymousVote
            } ?: throw NoSuchElementException("Voted candidate doesn't exist as object")

        return object : SinglePreferenceVote<S> {
            override var voter: Voter =
                object : Voter {
                    override val identifier: String
                        get() = uuid4().toString()
                }

            override var votedCompetitor: Competitor<S> = comp
        }
    }

    override infix fun List<String>.votedBy(voterIdentifier: String): ListOfPreferencesVote<S> {
        return this.asAnonymousVote().apply {
            this.voter = object : Voter {
                override val identifier: String
                    get() = voterIdentifier
            }
        }
    }

    override infix fun String.votedBy(voterIdentifier: String): SinglePreferenceVote<S> {
        return this.asAnonymousVote().apply {
            this.voter = object : Voter {
                override val identifier: String
                    get() = voterIdentifier
            }
        }
    }

    override infix fun List<String>.then(s: String): List<String> = this + s

    override infix fun String.then(s: String): List<String> = listOf(this, s)

    override operator fun PollAlgorithm<S, V>.unaryMinus() {
        this@PollAbstraction.pollAlgorithm = this@unaryMinus
    }

    override operator fun Competition<S>.unaryMinus() {
        this@PollAbstraction.competition = this@unaryMinus
    }

    override fun competition(competitionName: String, compInit: CompetitionDSL<S>.() -> Unit): Competition<S> {
        return object : CompetitionAbstraction<S>() {}
            .apply { this.competitionName = competitionName }
            .apply(compInit)
    }

    override operator fun V.unaryPlus() {
        if (!this@PollAbstraction::votesList.isInitialized) {
            this@PollAbstraction.votesList = listOf()
        }
        this@PollAbstraction.votesList += this@unaryPlus
    }
}
