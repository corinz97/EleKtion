package entities.interfaces

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
