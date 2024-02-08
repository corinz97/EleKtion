package entities.implementations

import entities.abstract.PollManagerAbstraction
import entities.interfaces.Poll
import entities.interfaces.PollManager
import entities.interfaces.Ranking
import entities.interfaces.Vote
import entities.interfaces.dsls.PollDSL
import entities.interfaces.dsls.PollManagerDSL
import entities.types.ScoreMetric

/**
 * This class allows to define and execute multiple polls.
 */
class PollManagerInstance<S : ScoreMetric, V : Vote> : PollManagerAbstraction<S, V>() {

    override fun computeAllPolls(): List<Ranking<S>> {
        val rankings = mutableListOf<Ranking<S>>()
        pollList.forEach { rankings.add(it.computePoll()) }
        return rankings.toList()
    }

    override fun printRankings() {
        val rankings = computeAllPolls()
        var i = 1
        println("*** Start of manager environment ***")
        println()
        println("** Start of rankings **")
        rankings.forEach {
            val rankingIndex = rankings.indexOf(it)
            println("** Ranking #${i++} **")
            println("Competition name is ${pollList[rankingIndex].competition.competitionName}")
            println("Used algorithm is ${pollList[rankingIndex].pollAlgorithm::class.simpleName}")
            println()
            it.printRanking()
            println()
        }
        println("** End of rankings **")

        println("*** End of manager environment ***")
        println()
    }

    override infix fun initializedAs(initializer: PollManagerDSL<S, V>.() -> Unit): PollManager<S, V> {
        return PollManagerInstance<S, V>()
            .apply(initializer)
    }
    override fun poll(newPoll: PollDSL<S, V>.() -> Unit): Poll<S, V> {
        return PollInstance<S, V>().apply(newPoll)
    }
}
