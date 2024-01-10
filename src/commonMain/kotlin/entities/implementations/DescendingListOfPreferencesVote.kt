package entities.implementations

import entities.interfaces.Competitor
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.Voter
import entities.types.ScoreMetric

/**
 * This class represents a list of candidate, inserted by most preferred first.
 */
class DescendingListOfPreferencesVote<S : ScoreMetric> : ListOfPreferencesVote<S> {
    override lateinit var votedCompetitors: List<Competitor<S>>
    override lateinit var voter: Voter
}
