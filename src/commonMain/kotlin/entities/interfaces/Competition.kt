package entities.interfaces

import entities.types.ScoreMetric

/**
 * This interface represents a competition.
 */
interface Competition<T : ScoreMetric> {

    /**
     * Name of the competition.
     */

    var competitionName: String

    /**
     * List of competitors joining the competition.
     */
    var competitors: List<Competitor<T>>

    /**
     * Shortcut to assign the value to [competitionName].
     */
    operator fun String.unaryMinus()

    /**
     * Shortcut to add an element in [competitors].
     */
    operator fun Competitor<T>.unaryPlus()

    /**
     * DSL-function used to initialize a competitor.
     */
    fun competitor(compInit: Competitor<T>.() -> Unit): Competitor<T>
}
