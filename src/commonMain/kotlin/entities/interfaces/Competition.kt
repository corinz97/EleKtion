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
}
