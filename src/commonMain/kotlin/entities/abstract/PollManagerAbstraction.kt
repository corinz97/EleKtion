package entities.abstract

import entities.interfaces.Poll
import entities.interfaces.PollManager
import entities.interfaces.Vote
import entities.interfaces.dsls.PollManagerDSL
import entities.types.ScoreMetric

/**
 * Provides an abstraction on poll manager.
 */
abstract class PollManagerAbstraction<S : ScoreMetric, V : Vote> : PollManager<S, V>, PollManagerDSL<S, V> {

    override lateinit var pollList: List<Poll<S, V>>

    override operator fun Poll<S, V>.unaryPlus() {
        if (!this@PollManagerAbstraction::pollList.isInitialized) {
            this@PollManagerAbstraction.pollList = listOf()
        }
        this@PollManagerAbstraction.pollList += this@unaryPlus
    }
}
