package entities.interfaces

/**
 * This interface represents the algorithm chosen to compute the final ranking.
 */
interface PollAlgorithm<S : ScoreMetric, V : Vote> {
    /**
     * List of parameters.
     */
    var pollAlgorithmParameters: List<PollAlgorithmParameter>

    /**
     * Compute the final ranking, given the votes.
     */
    fun computeByAlgorithmRules(votes: List<V>): Ranking<S>
}
