package entities.implementations

import com.benasher44.uuid.uuid4
import entities.abstract.PollAbstraction
import entities.interfaces.Competitor
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.SinglePreferenceVote
import entities.interfaces.Vote
import entities.interfaces.Voter
import entities.types.ScoreMetric

/**
 * This class allows to create a poll with its mandatory members.
 */
class PollInstance<S : ScoreMetric, V : Vote> : PollAbstraction<S, V>() {

    override fun List<String>.asAnonymousVote(): ListOfPreferencesVote<S> {
        if (this.isEmpty()) error("Votes list cannot be empty")

        val setOfCompetitors = this.toSet()
        val candidates = this@PollInstance.competition.competitors.map { it.name }.toSet()

        if (setOfCompetitors != candidates) { // mismatch between sets
            if ((setOfCompetitors - candidates).isNotEmpty()) {
                error("A list of preferences contains one o more not allowed candidate")
            }
            if ((candidates - setOfCompetitors).isNotEmpty()) {
                // every candidate must be present in the list of competitors
                error("Every allowed candidate must be present in every list of preferences")
            }
        }
        val groupCount = this.groupingBy { it }.eachCount()
        // every candidate can be present only once in the list of competitors
        if (groupCount.any { count -> count.value > 1 }) {
            error("Every allowed candidate can be present only once in the list of competitors")
        }

        val listOfCompetitorObject = mutableListOf<Competitor<S>>()
        this.forEach { actualName ->

            listOfCompetitorObject +=
                this@PollInstance.competition.competitors.firstOrNull { comp ->
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
            this@PollInstance.competition.competitors.firstOrNull {
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
}
