package entities.abstract

import entities.interfaces.Ranking
import entities.types.ScoreMetric

/**
 *
 */
abstract class RankingAbstraction<S : ScoreMetric> : Ranking<S> {
    override fun printRanking() = println(ranking.toString())
}
