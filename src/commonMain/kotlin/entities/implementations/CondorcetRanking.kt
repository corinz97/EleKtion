package entities.implementations

import entities.abstract.RankingAbstraction
import entities.interfaces.Competitor
import entities.interfaces.ScoreMetric

/**
 * Represents a ranking, without associated number of votes.
 */
class CondorcetRanking<S : ScoreMetric>(algOutput: List<Set<Competitor<S>>>) : RankingAbstraction<S>() {
    override val ranking: Map<Set<Competitor<S>>, Int?> = algOutput.associateWith { null }
}
