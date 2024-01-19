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

            group.forEach { competitor ->
                println("${competitor.name} scores list :")
                if (competitor.scores.isEmpty()) {
                    println("\t - no score available")
                }

                competitor.scores.forEach { score ->
                    print("\t  - Score type is ${score.scoreValue::class.simpleName} ")
                    print("with ${score.scoreValue}")
                    print(" -")
                    println()
                }
                println()
            }
        }
    }
}
