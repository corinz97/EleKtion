import kotlinx.serialization.Serializable
@Serializable
class Circuit {
    var circuitId: String? = null
    var url: String? = null
    var circuitName: String? = null


    var Location: Location? = null
}

@Serializable
class FirstPractice {
    var date: String? = null
    var time: String? = null
}

@Serializable
class Location {
    var lat: String? = null


    var long: String? = null
    var locality: String? = null
    var country: String? = null
}

@Serializable
class MRData {
    var xmlns: String? = null
    var series: String? = null
    var url: String? = null
    var limit: String? = null
    var offset: String? = null
    var total: String? = null


    var RaceTable: RaceTable? = null
}

@Serializable
class Qualifying {
    var date: String? = null
    var time: String? = null
}

@Serializable
class Race {
    var season: String? = null
    var round: String? = null
    var url: String? = null
    var raceName: String? = null


    var Circuit: Circuit? = null
    var date: String? = null
    var time: String? = null


    var FirstPractice: FirstPractice? = null


    var SecondPractice: SecondPractice? = null


    var ThirdPractice: ThirdPractice? = null


    var Qualifying: Qualifying? = null


    var Sprint: Sprint? = null
}

@Serializable
class RaceTable {
    var season: String? = null


    var Races: List<Race>? = null
}

@Serializable
class Root {

    var MRData: MRData? = null
}

@Serializable
class SecondPractice {
    var date: String? = null
    var time: String? = null
}

@Serializable
class Sprint {
    var date: String? = null
    var time: String? = null
}

@Serializable
class ThirdPractice {
    var date: String? = null
    var time: String? = null
}
