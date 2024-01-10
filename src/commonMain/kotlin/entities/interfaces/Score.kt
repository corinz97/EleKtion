package entities.interfaces

import entities.types.ScoreMetric

/**
 * This interface represents a score, valued depending on its type.
 */
interface Score<T : ScoreMetric> {
    /**
     * Score value.
     */
    var scoreValue: T
}
