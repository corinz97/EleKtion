package entities.interfaces

/**
 * Interface which represents a vote composed by a list of preferences,
 * instead of a single one.
 */
interface ListOfPreferencesVote<S : ScoreMetric> : Vote {
    /**
     * List of preferred competitors.
     */
    val votedCompetitors: List<Competitor<S>>
}
