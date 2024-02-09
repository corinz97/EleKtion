package entities.interfaces.dsls

import entities.interfaces.Competitor
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.PollAlgorithm
import entities.interfaces.Voter
import entities.types.ScoreMetric

/**
 * DSL for initialize list of preferences vote algorithms.
 */
interface ListOfPreferencesVoteAlgorithmDSL<S : ScoreMetric> {
    /**
     * DSL-function which initializes Condorcet algorithm.
     */
    fun condorcetAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>>

    /**
     * DSL-function which initializes Schultze algorithm.
     */
    fun schultzeAlgorithm(
        algorithmInit: PollAlgorithmDSL.() -> Unit,
    ): PollAlgorithm<S, ListOfPreferencesVote<S>>

    /**
     * Shortcut which allows competitors' name chaining in the list.
     */
    infix fun List<String>.then(s: String): List<String>

    /**
     * Shortcut which allows competitors' name chaining in the list.
     */
    infix fun String.then(s: String): List<String>

    /**
     * This function allows a  shortcut to create a [ListOfPreferencesVote],
     * given the name list of [Competitor] voted by a [Voter], distinguished by its identifier.
     */
    infix fun List<String>.votedBy(voterIdentifier: String): ListOfPreferencesVote<S>

    /**
     * This function allows a  shortcut to create a [ListOfPreferencesVote],
     * given the name list of [Competitor]. [Voter] identifier is randomly generated.
     */
    fun List<String>.asAnonymousVote(): ListOfPreferencesVote<S>
}
