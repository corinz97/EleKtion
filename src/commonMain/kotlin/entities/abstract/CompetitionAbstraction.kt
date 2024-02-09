package entities.abstract

import entities.interfaces.Competition
import entities.interfaces.Competitor
import entities.interfaces.dsls.CompetitionDSL
import entities.interfaces.dsls.CompetitorDSL
import entities.types.ScoreMetric

/**
 *
 */
abstract class CompetitionAbstraction<T : ScoreMetric> : Competition<T>, CompetitionDSL<T> {
    override lateinit var competitionName: String
    override lateinit var competitors: List<Competitor<T>>

    override operator fun Competitor<T>.unaryPlus() {
        if (!this@CompetitionAbstraction::competitors.isInitialized) {
            this@CompetitionAbstraction.competitors = listOf()
        }
        if (this@CompetitionAbstraction.competitors.map { it.name }.contains(this.name)) {
            error("Candidate already declared")
        }
        this@CompetitionAbstraction.competitors += this
    }

    override fun competitor(competitorName: String, competitorInit: CompetitorDSL<T>.() -> Unit): Competitor<T> {
        return object : CompetitorAbstraction<T>() {
        }
            .apply { this.name = competitorName }
            .apply(competitorInit)
    }
}
