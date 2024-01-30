@file:Suppress("ktlint:standard:no-wildcard-imports")

package entities.implementations

import entities.interfaces.Competitor
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.PollAlgorithm
import entities.interfaces.PollAlgorithmParameter
import entities.interfaces.Ranking
import entities.types.ConstantParameter
import entities.types.ScoreMetric

/**
 * Represents an algorithm implementing Condorcet logic.
 */
class SchultzeAlgorithm<S : ScoreMetric>(
    override var pollAlgorithmParameters: List<PollAlgorithmParameter> = listOf(),
) :
    PollAlgorithm<S, ListOfPreferencesVote<S>> {
    /**
     * List of candidates, useful to check pre-conditions before executing
     * the computation.
     */
    lateinit var candidates: List<Competitor<S>>

    override fun computeByAlgorithmRules(votes: List<ListOfPreferencesVote<S>>): Ranking<S> {
        if (candidates.groupingBy { it.name }.eachCount().any { it.value > 1 }) {
            error("Candidate already declared")
        }

        require(votes.any())
        // if (votes.isEmpty()) throw IllegalArgumentException("Votes list cannot be empty")

        when (pollAlgorithmParameters.count { it == ConstantParameter.AllowMultipleVoteInPollParameter }) {
            0 -> {
                if (votes.groupingBy { it.voter.identifier }.eachCount().any { it.value > 1 }) {
                    error("Each voter can vote only once")
                }
            }
            1 -> {
                // multiple vote allowed
                if (votes.groupingBy {
                        Pair(it.votedCompetitors.map { c -> c.name }, it.voter.identifier)
                    }.eachCount().any { it.value > 1 }
                ) {
                    error("Each voter can vote just once for each list of preferences")
                }
            }
            else -> error("Parameter can't be repeated more than once")
        }

        votes.map { it.votedCompetitors }.forEach {
            val setOfCompetitors = it.toSet()

            if (setOfCompetitors != candidates) { // mismatch between sets
                if ((setOfCompetitors - candidates).isNotEmpty()) {
                    error("A list of preferences contains one o more not allowed candidate")
                }
                if ((candidates - setOfCompetitors).isNotEmpty()) {
                    // every candidate must be present in the list of competitors
                    error("Every allowed candidate must be present in every list of preferences")
                }
            }

            val groupCount = it.groupingBy { comp -> comp }.eachCount()
            // every candidate can be present only once in the list of competitors
            if (groupCount.any { count -> count.value > 1 }) {
                error(
                    "Every allowed candidate can be present only once in the list of competitors",
                )
            }
        }

        val result = calculateWinners(candidates.toList(), votes)

        return CondorcetRanking(result)
    }

    private fun <S : ScoreMetric> calculateVoteMatrix(
        candidates: List<Competitor<S>>,
        ballots: List<ListOfPreferencesVote<S>>,
    ): Array<IntArray> {
        val numCandidates = candidates.size
        val voteMatrix = Array(numCandidates) { IntArray(numCandidates) }

        for (ballot in ballots) {
            for (i in 0 until numCandidates) {
                for (j in i + 1 until numCandidates) {
                    val candidate1 = candidates[i]
                    val candidate2 = candidates[j]

                    val indexCandidate1 =
                        ballot.votedCompetitors.indexOfFirst {
                            it.name == candidate1.name
                        } // ballot.votedCompetitors.indexOf(candidate1)
                    val indexCandidate2 =
                        ballot.votedCompetitors.indexOfFirst {
                            it.name == candidate2.name
                        } // ballot.votedCompetitors.indexOf(candidate2)

                    if (indexCandidate1 < indexCandidate2) {
                        voteMatrix[i][j]++
                    } else {
                        voteMatrix[j][i]++
                    }
                }
            }
        }

        return voteMatrix
    }

    /*private fun <S : ScoreMetric> roundWinner(
        candidatesOriginalList: List<Competitor<S>>,
        candidatesCurrentList: List<Competitor<S>>,
        matrix: Array<IntArray>,
    ): Set<Competitor<S>>? {
        var maxVotes = 0
        var c: Set<Competitor<S>>? = null
        candidatesOriginalList.indices.forEach { i ->
            var victories = 0
            var defeats = 0

            candidatesOriginalList.indices.forEach { j ->
                if (i != j) {
                    if (matrix[i][j] > matrix[j][i]) {
                        victories++
                    } else if (matrix[i][j] < matrix[j][i]) {
                        defeats++
                    }
                }
            }
            /*
            //Version without ties
                if (victories >= defeats) {
                if (candidatesCurrentList.size == 1) {
                    c = candidatesCurrentList.first()
                } else if (matrix[i].sum() > maxVotes) {
                    maxVotes = matrix[i].sum()
                    c = candidatesOriginalList[i]
                }
            }
     */

            // Version with ties
            if (victories >= defeats) {
                if (candidatesCurrentList.size == 1) {
                    if (c == null) c = setOf()
                    c = c!! + candidatesCurrentList.first()
                } else if (matrix[i].sum() >= maxVotes) {
                    if (c == null) c = setOf()
                    if (matrix[i].sum() > maxVotes) {
                        c = setOf(candidatesOriginalList[i])
                    }
                    if (matrix[i].sum() == maxVotes) {
                        c = c!! + candidatesOriginalList[i]
                    }
                    maxVotes = matrix[i].sum()
                }
            }
        }
        return c
    }*/

    private fun <S : ScoreMetric> roundWinner(
        candidatesOriginalList: List<Competitor<S>>,
        candidatesCurrentList: List<Competitor<S>>,
        matrix: Array<IntArray>,
    ): Set<Competitor<S>>? {
        var c: Set<Competitor<S>>? = setOf()
        var maxSum = 0

        candidatesOriginalList.indices.forEach { i ->
            if (matrix[i].sum() >= maxSum && candidatesOriginalList[i] in candidatesCurrentList) {
                if (matrix[i].sum() > maxSum) {
                    c = setOf(candidatesOriginalList[i])
                }
                if (matrix[i].sum() == maxSum) {
                    c = c!! + candidatesOriginalList[i]
                }

                maxSum = matrix[i].sum()
            }
        }
        return c
    }

    private fun <S : ScoreMetric> calculateWinners(
        candidatesOriginalList: List<Competitor<S>>,
        ballots: List<ListOfPreferencesVote<S>>,
    ): List<Set<Competitor<S>>> {
        val voteMatrix = calculateVoteMatrix(candidatesOriginalList.toList(), ballots)
        val winners = mutableListOf<Set<Competitor<S>>>()
        val candidatesCurrentList = candidatesOriginalList.toMutableList()
        var k = 0
        while (k < candidatesOriginalList.size && candidatesCurrentList.any()) {
            val result = roundWinner(candidatesOriginalList.toList(), candidatesCurrentList.toList(), voteMatrix)

            if (result != null && result.any()) {
                winners.add(result)
                // set to 0 the preferences, in order to ignore it in next cycle
                for (w in result) {
                    // reset matrix row of winner
                    for (j in candidatesOriginalList.indices) {
                        voteMatrix[
                            candidatesOriginalList.indexOfFirst {
                                it.name == w.name
                            },
                        ][j] = 0
                    }
                    // remove actual winner from the list
                    candidatesCurrentList.remove(w)
                }
            }
            k++
        }

        return winners.toList()
    }
}
