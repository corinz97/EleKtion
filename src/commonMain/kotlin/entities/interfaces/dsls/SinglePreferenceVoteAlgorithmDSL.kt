package entities.interfaces.dsls

import entities.interfaces.Competitor
import entities.interfaces.PollAlgorithm
import entities.interfaces.SinglePreferenceVote
import entities.interfaces.Voter
import entities.types.ScoreMetric

/**
 * DSL for single preference vote algorithms.
 */
interface SinglePreferenceVoteAlgorithmDSL<S : ScoreMetric> {

    /**
     * DSL-function which initializes  MajorityVotes algorithm .
     */
    fun majorityVotesAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * DSL-function which initializes MajorityVotesThenHighestScore algorithm.
     */
    fun majorityVotesHScoreAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * DSL-function which initializes MajorityVotesThenLowestScore algorithm.
     */
    fun majorityVotesLScoreAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, SinglePreferenceVote<S>>

    /**
     * This function allows a  shortcut to create a [SinglePreferenceVote],
     * given the name of [Competitor] voted by a [Voter], distinguished by its identifier.
     */
    infix fun String.votedBy(voterIdentifier: String): SinglePreferenceVote<S>

    /**
     * This function allows a  shortcut to create a [SinglePreferenceVote],
     * given the name of [Competitor]. [Voter] identifier is randomly generated.
     */
    fun String.asAnonymousVote(): SinglePreferenceVote<S>
}
