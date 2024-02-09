package entities.interfaces.dsls

import entities.interfaces.Competitor
import entities.types.ScoreMetric

/**
 * DSL for competitions.
 */
interface CompetitionDSL<T : ScoreMetric> {
    /**
     * Shortcut to add an element in competitors.
     */
    operator fun Competitor<T>.unaryPlus()

    /**
     * DSL-function used to initialize a competitor.
     */
    fun competitor(competitorName: String, competitorInit: CompetitorDSL<T>.() -> Unit): Competitor<T>
}
