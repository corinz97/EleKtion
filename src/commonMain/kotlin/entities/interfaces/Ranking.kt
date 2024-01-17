package entities.interfaces

import entities.types.ScoreMetric

/**
 * This interface represents the ranking obtained after the poll computation.
 */
interface Ranking<S : ScoreMetric> {
    /**
     * Map of competitors and relative votes. Set
     * allows to manage ties of votes and/or score.
     */
    val ranking: Map<Set<Competitor<S>>, Int?>

    /**
     * Print the ranking.
     */
    fun printRanking()
}
