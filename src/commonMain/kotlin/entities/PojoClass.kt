
import kotlinx.serialization.Serializable
@Serializable
class CircuitType {
    var circuitId: String? = null
    var url: String? = null
    var circuitName: String? = null

    var Location: LocationType? = null
}

@Serializable
class FirstPracticeType {
    var date: String? = null
    var time: String? = null
}

@Serializable
class LocationType {
    var lat: String? = null

    var long: String? = null
    var locality: String? = null
    var country: String? = null
}

@Serializable
class MRDataType {
    var xmlns: String? = null
    var series: String? = null
    var url: String? = null
    var limit: String? = null
    var offset: String? = null
    var total: String? = null

    var RaceTable: RaceTableType? = null
}

@Serializable
class QualifyingType {
    var date: String? = null
    var time: String? = null
}

@Serializable
class RaceType {
    var season: String? = null
    var round: String? = null
    var url: String? = null
    var raceName: String? = null

    var Results: Array<ResultType>? = null
    var Circuit: CircuitType? = null
    var date: String? = null
    var time: String? = null

    var FirstPractice: FirstPracticeType? = null

    var SecondPractice: SecondPracticeType? = null

    var ThirdPractice: ThirdPracticeType? = null

    var Qualifying: QualifyingType? = null

    var Sprint: SprintType? = null
}

@Serializable
class ResultType {

    var number: Int? = null

    var position: Int? = null

    var positionText: String? = null

    var points: Float? = null

    var Driver: DriverType? = null

    var Constructor: ConstructorType? = null

    var grid: Int? = null

    var laps: Int? = null

    var status: String? = null

    var Time: DurationType? = null

    var FastestLap: FastestLapType? = null
}

@Serializable
class FastestLapType {
    var time: DurationType? = null

    var averageSpeed: SpeedType? = null

    var rank: Int? = null

    var lap: Int? = null
}

@Serializable
class DurationType {

    var value: String? = null

    var millis: Long? = null
}

@Serializable
class SpeedType {

    var speed = 0f

    var units: String? = null
}

@Serializable
class ConstructorType {

    var name: String? = null

    var nationality: String? = null

    var constructorId: String? = null

    var url: String? = null
}

@Serializable
class DriverType {
    var permanentNumber: Int? = null

    var givenName: String? = null

    var familyName: String? = null

    var dateOfBirth: String? = null

    var nationality: String? = null

    var driverId: String? = null

    var url: String? = null
}

@Serializable
class RaceTableType {
    var season: String? = null
    var round: String? = null
    var Races: Array<RaceType>? = null
}

@Serializable
class RootType {

    var MRData: MRDataType? = null
}

@Serializable
class SecondPracticeType {
    var date: String? = null
    var time: String? = null
}

@Serializable
class SprintType {
    var date: String? = null
    var time: String? = null
}

@Serializable
class ThirdPracticeType {
    var date: String? = null
    var time: String? = null
}
