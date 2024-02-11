package entities.interfaces

/**
 * Represents a metric useful two compare and evaluate results among competitors.
 */
interface ScoreMetric : Comparable<Any> {
    /**
     * Metric to compare two scores.
     */
    override fun compareTo(other: Any): Int

    /**
     * Print score information.
     */
    override fun toString(): String
}
