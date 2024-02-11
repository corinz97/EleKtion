package entities.types
import entities.abstract.ScoreAbstraction
import entities.interfaces.Score
import entities.interfaces.ScoreMetric
import kotlin.time.Duration

/**
 * This class represents an abstraction for best time realized during a match.
 * @param duration time, with time unit associated
 */
data class BestTimeInMatch(val duration: Duration) : ScoreMetric {
    /**
     * Compare two objects.
     */
    override fun compareTo(other: Any): Int {
        require(other is BestTimeInMatch)
        return duration.compareTo(other.duration)
    }

    override fun toString(): String {
        return "time = $duration"
    }

    companion object {
        /**
         * Returns an object which implements ScoreScore<BestTimeInMatch>, given the duration.
         *  @param duration time, with time unit associated
         */
        infix fun Companion.realized(duration: Duration): Score<BestTimeInMatch> =
            object : ScoreAbstraction<BestTimeInMatch>() {
                override var scoreValue: BestTimeInMatch = BestTimeInMatch(duration)
            }
    }
}

/**
 * This class represents an abstraction for wins realized during a championship.
 * @param wins number of wins
 */

data class WinsInCampionship(val wins: Int) : ScoreMetric {
    /**
     * Compare two objects.
     */
    override fun compareTo(other: Any): Int {
        require(other is WinsInCampionship)
        return wins.compareTo(other.wins)
    }

    override fun toString(): String {
        return "wins = $wins"
    }

    companion object {
        /**
         * Returns an object which implements Score<WinsInCampionship>, given the number of wins.
         *  @param wins number of wins
         */
        infix fun Companion.realized(wins: Int): Score<WinsInCampionship> =
            object : ScoreAbstraction<WinsInCampionship>() {
                override var scoreValue: WinsInCampionship = WinsInCampionship(wins)
            }
    }
}

/**
 * This class represents an abstraction for points realized during a race.
 * @param points number of points
 */

data class PointsInRace(val points: Int) : ScoreMetric {
    /**
     * Compare two objects.
     */
    override fun compareTo(other: Any): Int {
        require(other is PointsInRace)
        return points.compareTo(other.points)
    }

    override fun toString(): String {
        return "points = $points"
    }

    companion object {
        /**
         * Returns an object which implements Score<PointsInRace>, given the number of points.
         *  @param points number of points
         */
        infix fun Companion.realized(points: Int): Score<PointsInRace> =
            object : ScoreAbstraction<PointsInRace>() {
                override var scoreValue: PointsInRace = PointsInRace(points)
            }
    }
}

/**
 * This class represents an abstraction for average speed realized during the fastest lap in a race.
 * @param speed average speed realized
 */
data class FastestLapAvgSpeed(val speed: Float) : ScoreMetric {
    /**
     * Compare two objects.
     */
    override fun compareTo(other: Any): Int {
        require(other is FastestLapAvgSpeed)
        return speed.compareTo(other.speed)
    }

    override fun toString(): String {
        return "fastestLapAvgSpeed = $speed km/h"
    }

    companion object {
        /**
         * Returns an object which implements Score<FastestLapAvgSpeed>, given the speed.
         *  @param speed average speed realized
         */
        infix fun Companion.realized(speed: Float): Score<FastestLapAvgSpeed> =
            object : ScoreAbstraction<FastestLapAvgSpeed>() {
                override var scoreValue: FastestLapAvgSpeed = FastestLapAvgSpeed(speed)
            }
    }
}
