package entities.interfaces

/**
 * This inteface represents the poll to be evaluated.
 */
interface Poll<S : ScoreMetric, V : Vote> {
    /**
     * Algorithm chosen to compute the final ranking.
     */
    var pollAlgorithm: PollAlgorithm<S, V>

    /**
     * Definition of competition and its members.
     */
    var competition: Competition<S>

    /**
     * Definition of votes.
     */
    var votesList: List<V>

    /**
     * Computes the final ranking.
     */
    fun computePoll(): Ranking<S>
}
