
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* @Serializable
class CircuitType {
    var circuitId: String? = null
    var url: String? = null
    var circuitName: String? = null

    var Location: LocationType? = null
}*/

/* @Serializable
class FirstPracticeType {
    var date: String? = null
    var time: String? = null
}*/

/* @Serializable
class LocationType {
    var lat: String? = null

    var long: String? = null
    var locality: String? = null
    var country: String? = null
}*/
/**
 * Main node of Ergast API.
 */
@Serializable
class MRDataType {
    // var xmlns: String? = null
    // var series: String? = null
    // var url: String? = null
    // var limit: String? = null
    // var offset: String? = null
    // var total: String? = null

    /**
     * Races container.
     */
    @SerialName("RaceTable")
    var raceTable: RaceTableType? = null
}

/* @Serializable
 class QualifyingType {
    var date: String? = null
    var time: String? = null
}*/
/**
 * Race node of Ergast API.
 */
@Serializable
class RaceType {
    /**
     * Championship season.
     */
    var season: String? = null

    /**
     * Number of round in championship.
     */
    var round: String? = null

    // var url: String? = null

    /**
     * Place where round happens.
     */
    var raceName: String? = null

    /**
     * List of results.
     */
    @SerialName("Results")
    var results: Array<ResultType>? = null

    // var Circuit: CircuitType? = null
    // var date: String? = null
    // var time: String? = null

    // var FirstPractice: FirstPracticeType? = null

    // var SecondPractice: SecondPracticeType? = null

    // var ThirdPractice: ThirdPracticeType? = null

    // var Qualifying: QualifyingType? = null

    // var Sprint: SprintType? = null
}

/**
 * Result node of Ergast API.
 */
@Serializable
class ResultType {

    /**
     * Driver's number.
     */
    var number: Int? = null

    // var position: Int? = null

    // var positionText: String? = null
    /**
     * Points in race
     */
    var points: Float? = null

    /**
     * Driver node of Ergast API.
     */
    @SerialName("Driver")
    var driver: DriverType? = null

    /**
     * Constructor node in Ergast API
     */
    @SerialName("Constructor")
    var constructorNode: ConstructorType? = null

    // var grid: Int? = null

    // var laps: Int? = null

    // var status: String? = null

    // var Time: DurationType? = null
    /**
     * FastestLap node in Ergast API.
     */
    @SerialName("FastestLap")
    var fastestLap: FastestLapType? = null
}

/**
 * FastestLap node in Ergast API.
 */
@Serializable
class FastestLapType {
    /**
     * Time in the fastest lap.
     */

    @SerialName("Time")
    var time: DurationType? = null

    /**
     * AverageSpeed node in Ergast API.
     */
    @SerialName("AverageSpeed")
    var averageSpeed: SpeedType? = null

    // var rank: Int? = null

    // var lap: Int? = null
}

/**
 * Type of time in Ergast API.
 */
@Serializable
class DurationType {
    /**
     * Time measurement.
     */
    var time: String? = null

    // var millis: Long? = null
}

/**
 * AverageSpeed node in Ergast API.
 */
@Serializable
class SpeedType {

    /**
     * Speed value.
     */
    var speed = 0f

    /**
     * Units of speed.
     */
    var units: String? = null
}

@Serializable
class ConstructorType {

    /**
     * Constructor name
     */
    var name: String? = null

    // var nationality: String? = null

    // var constructorId: String? = null

    // var url: String? = null
}

/**
 * Driver node of Ergast API.
 */
@Serializable
class DriverType {
    // var permanentNumber: Int? = null
    /**
     * Driver's name.
     */
    var givenName: String? = null

    /**
     * Driver's surname.
     */
    var familyName: String? = null

    // var dateOfBirth: String? = null

    // var nationality: String? = null

    // var driverId: String? = null

    // var url: String? = null
}

/**
 * /**
 *  * RaceTable node of Ergast API.
 *  */
 */
@Serializable
class RaceTableType {
    // var season: String? = null
    // var round: String? = null
    /**
     * List of races.
     */
    @SerialName("Races")
    var races: Array<RaceType>? = null
}

/**
 * Entry point of Ergast API.
 */
@Serializable
class RootType {
    /**
     * Main node of Ergast API.
     */
    @SerialName("MRData")
    var mRData: MRDataType? = null
}

/* @Serializable
class SecondPracticeType {
    var date: String? = null
    var time: String? = null
}*/

/* @Serializable
class SprintType {
    var date: String? = null
    var time: String? = null
}*/

/* @Serializable
 class ThirdPracticeType {
    var date: String? = null
    var time: String? = null
}*/
