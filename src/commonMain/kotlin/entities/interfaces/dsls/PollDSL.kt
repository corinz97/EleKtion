package entities.interfaces.dsls

import entities.interfaces.Competition
import entities.interfaces.PollAlgorithm
import entities.interfaces.ScoreMetric
import entities.interfaces.Vote

/**
 * DSL for polls.
 */
interface PollDSL<S : ScoreMetric, V : Vote> :
    SinglePreferenceVoteAlgorithmDSL<S>,
    ListOfPreferencesVoteAlgorithmDSL<S> {
/**
     * Shortcut which assigns the value to pollAlgorithm.
     */
    operator fun PollAlgorithm<S, V>.unaryMinus()

/**
     * Shortcut which assigns the value to [competition].
     */
    operator fun Competition<S>.unaryMinus()

/**
     * Shortcut to add a vote in votesList.
     */
    operator fun V.unaryPlus()

/**
     * DSL-function which initializes a [Competition].
     */
    fun competition(competitionName: String, competitionInit: CompetitionDSL<S>.() -> Unit): Competition<S>
}
