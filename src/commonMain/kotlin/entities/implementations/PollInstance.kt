package entities.implementations

import entities.abstract.PollAbstraction
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.PollAlgorithm
import entities.interfaces.ScoreMetric
import entities.interfaces.SinglePreferenceVote
import entities.interfaces.Vote
import entities.interfaces.dsls.PollAlgorithmDSL

/**
 * This class allows to create a poll with its mandatory members.
 */
class PollInstance<S : ScoreMetric, V : Vote> : PollAbstraction<S, V>() {

    private inline fun <reified A> Any.cast(): A? {
        if (this !is A) return null
        return this
    }
    override fun majorityVotesAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>> {
        val a =
            MajorityVotesAlgorithm<S>()
                .apply {
                    this.candidates = this@PollInstance.competition.competitors
                }
                .apply(algorithmInit)
        return a.cast<PollAlgorithm<S, SinglePreferenceVote<S>>>()!!
    }

    override fun majorityVotesHScoreAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>> {
        val a =
            MajorityVotesThenHighestScoreAlgorithm<S>()
                .apply {
                    this.candidates = this@PollInstance.competition.competitors
                }
                .apply(algorithmInit)
        return a.cast<PollAlgorithm<S, SinglePreferenceVote<S>>>()!!
    }

    override fun majorityVotesLScoreAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>> {
        val a =
            MajorityVotesThenLowestScoreAlgorithm<S>()
                .apply {
                    this.candidates = this@PollInstance.competition.competitors
                }
                .apply(algorithmInit)
        return a.cast<PollAlgorithm<S, SinglePreferenceVote<S>>>()!!
    }

    override fun condorcetAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>> {
        val a =
            CondorcetAlgorithm<S>()
                .apply {
                    this.candidates = this@PollInstance.competition.competitors
                }
                .apply(algorithmInit)
        return a.cast<PollAlgorithm<S, ListOfPreferencesVote<S>>>()!!
    }

    override fun schultzeAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>> {
        val a =
            SchultzeAlgorithm<S>()
                .apply {
                    this.candidates = this@PollInstance.competition.competitors
                }
                .apply(algorithmInit)
        return a.cast<PollAlgorithm<S, ListOfPreferencesVote<S>>>()!!
    }
}
