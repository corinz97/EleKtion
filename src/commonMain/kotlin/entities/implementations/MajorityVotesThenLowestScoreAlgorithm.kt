@file:Suppress("ktlint:standard:no-wildcard-imports")

package entities.implementations

import entities.interfaces.Competitor
import entities.interfaces.PollAlgorithm
import entities.interfaces.PollAlgorithmParameter
import entities.interfaces.Ranking
import entities.interfaces.SinglePreferenceVote
import entities.interfaces.dsls.PollAlgorithmDSL
import entities.types.ConstantParameter
import entities.types.ScoreMetric

/**
 * Represents an algorithm which orders intermediate ranking by descending
 * number of votes, then by lowest score (about ties).
 */
class MajorityVotesThenLowestScoreAlgorithm<S : ScoreMetric>(
    override var pollAlgorithmParameters: List<PollAlgorithmParameter> = listOf(),
) :
    PollAlgorithm<S, SinglePreferenceVote<S>>, PollAlgorithmDSL {
    /**
     * List of candidates, useful to check pre-conditions before executing
     * the computation.
     */
    lateinit var candidates: List<Competitor<S>>

    override fun computeByAlgorithmRules(votes: List<SinglePreferenceVote<S>>): Ranking<S> {
        if (candidates.groupingBy { it.name }.eachCount().any { it.value > 1 }) {
            error("Candidate already declared")
        }

        require(votes.any())
        // if (votes.isEmpty()) throw IllegalArgumentException("Votes list cannot be empty")

        if (votes.map { it.votedCompetitor }.any { it !in candidates }) {
            error("Voted candidate doesn't exist as object")
        }

        when (pollAlgorithmParameters.count { it == ConstantParameter.AllowMultipleVoteInPollParameter }) {
            0 -> {
                if (votes.groupingBy { it.voter.identifier }.eachCount().any { it.value > 1 }) {
                    error("Each voter can vote only once")
                }
            }
            1 -> {
                // multiple vote allowed
                if (votes.groupingBy {
                        Pair(
                            it.votedCompetitor.name,
                            it.voter.identifier,
                        )
                    }.eachCount().any { it.value > 1 }
                ) {
                    error("Each voter can vote just once for each competitor")
                }
            }
            else -> throw IllegalArgumentException("Parameter can't be repeated more than once")
        }

        var votesCount = votes.groupingBy { it.votedCompetitor }.eachCount()

        val defaultNoneVotes = candidates.filter { c -> c !in votes.map { it.votedCompetitor } }.associateWith { 0 }
        // insert non-voted candidates as 0
        votesCount = votesCount + defaultNoneVotes

        val maxValue = votesCount.values.max()

        return RankingByDescendingVotesThenLowestScore(votesCount.filterValues { it == maxValue })
    }
}
