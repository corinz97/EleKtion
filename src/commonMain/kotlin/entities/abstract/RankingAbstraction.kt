package entities.abstract

import entities.interfaces.Ranking
import entities.types.ScoreMetric

/**
 *
 */
abstract class RankingAbstraction<S : ScoreMetric> : Ranking<S> {
    override fun printRanking() {
        println()
        var i = 1
        for ((group, votes) in ranking) {
            print("Placement #${i++} -> ")
            print(
                group.joinToString(
                    separator = " | ",
                    prefix = "[",
                    postfix = "]",
                ) { it.name },
            )

            val votesPrint =
                when (votes) {
                    is Int -> " with $votes votes "
                    else -> ""
                }

            print(votesPrint)
            println()
            val scoresPrint = group.flatMap { it.scores }.toSet() // managing ties
            scoresPrint.forEach {
                print("\t  - Score type is ${it.scoreValue::class.simpleName} ")
                print("with ${it.scoreValue}")
                print(" -")
            }

            println()
        }
    }
}
