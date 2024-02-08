package entities.interfaces

import entities.types.ScoreMetric
/**
 * This interface represents a manager that builds then executes one or more poll.
 */
interface PollManager<S : ScoreMetric, V : Vote> {

    /**
     * List of polls to be managed.
     */
    var pollList: List<Poll<S, V>>

    /**
     * Computes every [Poll] defined, return a list of [Ranking].
     */
    fun computeAllPolls(): List<Ranking<S>>

    /**
     * Print all rankings.
     */
    fun printRankings()
}
