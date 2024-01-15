package entities.implementations

import entities.interfaces.Poll
import entities.interfaces.PollManager
import entities.interfaces.Ranking
import entities.interfaces.Vote
import entities.types.ScoreMetric

/**
 * This class allows to define and execute multiple polls.
 */
class PollManagerInstance<S : ScoreMetric, V : Vote> : PollManager<S, V> {
    override lateinit var pollList: List<Poll<S, V>>

    override fun computeAllPolls(): List<Ranking<S>> {
        val rankings = mutableListOf<Ranking<S>>()
        pollList.forEach { rankings.add(it.computePoll()) }
        return rankings.toList()
    }

    override infix fun initializedAs(initializer: PollManager<S, V>.() -> Unit): PollManager<S, V> {
        return PollManagerInstance<S, V>()
            .apply(initializer)
    }

    override operator fun Poll<S, V>.unaryPlus() {
        if (!this@PollManagerInstance::pollList.isInitialized) {
            this@PollManagerInstance.pollList = listOf()
        }
        this@PollManagerInstance.pollList += this@unaryPlus
    }

    override fun poll(newPoll: Poll<S, V>.() -> Unit): Poll<S, V> {
        return PollInstance<S, V>().apply(newPoll)
    }
}
