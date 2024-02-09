package entities.interfaces.dsls

import entities.interfaces.Poll
import entities.interfaces.PollManager
import entities.interfaces.Vote
import entities.types.ScoreMetric

/**
 * DSL for poll manager.
 */
interface PollManagerDSL<S : ScoreMetric, V : Vote> {
    /**
     * DSL-function useful to initialize the manager.
     */
    infix fun initializedAs(initializer: PollManagerDSL<S, V>.() -> Unit): PollManager<S, V>

    /**
     * Shortcut to add a poll in [pollList].
     */
    operator fun Poll<S, V>.unaryPlus()

    /**
     * DSL-function useful to initialize a poll.
     */
    fun poll(pollInit: PollDSL<S, V>.() -> Unit): Poll<S, V>
}
