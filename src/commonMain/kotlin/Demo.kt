
import entities.implementations.PollManagerInstance
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.SinglePreferenceVote
import entities.types.BestTimeInMatch
import entities.types.BestTimeInMatch.Companion.realized
import entities.types.ConstantParameter
import entities.types.PointsInRace
import entities.types.PointsInRace.Companion.realized
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private const val T = 0.1

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    decodeEnumsCaseInsensitive = true
    allowSpecialFloatingPointValues = true
}

/* private fun String.parseTime(): Duration {
    val parts = this.split(":")
    val minutes = parts[0].toLong()
    val secondsAndMilliseconds = parts[1].split(".")
    val seconds = secondsAndMilliseconds[0].toLong()
    val milliseconds = secondsAndMilliseconds[1].toLong()
    return minutes.toDuration(DurationUnit.MINUTES) +
        seconds.toDuration(DurationUnit.SECONDS) +
        milliseconds.toDuration(DurationUnit.MILLISECONDS)
}*/

/**
 * Demo main function.
 */

suspend fun main() {
    val a =
        PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                    }

                    +competitor("competitor2") {
                    }
                }
                -majorityVotesAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // competitor1 wins

            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter3")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // competitor1 and competitor2 have tie in votes
        }
    println("Example #1 MajorityVotesAlgorithm -> competitor1 wins")
    println("Example #2 MajorityVotesAlgorithm -> competitor1 and competitor2 have tie in votes")
    a.printRankings()

    val b =
        PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesHScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same score, competitor2 has more votes

            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesHScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same votes, competitor1 has the highest score

            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesHScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same votes, same highest score
        }

    println("Example #1 MajorityVotesThenHighestScoreAlgorithm -> same score, competitor2 has more votes")
    println("Example #2 MajorityVotesThenHighestScoreAlgorithm -> same votes, competitor1 has the highest score")
    println("Example #3 MajorityVotesThenHighestScoreAlgorithm -> same votes, same highest score")
    b.printRankings()

    val c =
        PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesLScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same score, competitor2 has more votes

            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesLScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same votes, competitor1 has the lowest score

            +poll {
                -competition("Race") {
                    +competitor("competitor1") {
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor("competitor2") {
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesLScoreAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("competitor2" votedBy "voter1")
                +("competitor1" votedBy "voter1")
                +("competitor2" votedBy "voter2")
                +("competitor1" votedBy "voter2")
            } // same votes, same lowest score
        }

    println("Example #1 MajorityVotesThenLowestScoreAlgorithm -> same score, competitor2 has more votes")
    println("Example #2 MajorityVotesThenLowestScoreAlgorithm -> same votes, competitor1 has the lowest score")
    println("Example #3 MajorityVotesThenLowestScoreAlgorithm -> same votes, same lowest score")

    c.printRankings()

    var counter = 1
    val d =
        PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("Sport match") {
                    +competitor("competitorB") {
                    }

                    +competitor("competitorA") {
                    }

                    +competitor("competitorC") {
                    }
                }
                -condorcetAlgorithm {}

                +("competitorA" then "competitorB" then "competitorC" votedBy "anonym" + counter++)
                +("competitorA" then "competitorB" then "competitorC" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorB" then "competitorA" then "competitorC" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
            } // competitorC, competitorA, competitorB
        }
    println("Example #1 CondorcetAlgorithm -> Condorcet result is competitorC - competitorA - competitorB")
    d.printRankings()

    counter = 1
    val e =
        PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("Sport match") {
                    +competitor("competitorB") {
                    }

                    +competitor("competitorA") {
                    }

                    +competitor("competitorC") {
                    }
                }
                -schultzeAlgorithm {}

                +("competitorA" then "competitorB" then "competitorC" votedBy "anonym" + counter++)
                +("competitorA" then "competitorB" then "competitorC" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorB" then "competitorA" then "competitorC" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
            } // competitorC, competitorA, competitorB
        }
    println("Example #1 SchultzeAlgorithm -> Schultze result is competitorC - competitorA - competitorB")
    e.printRankings()

    println("Press Enter key to continue")
    readln()

    fun1()
    fun2()
    // fun3()
    // fun4()
    fun5()
}

private suspend fun fun1() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2023.json")
    println("Downloading championship data...")

    val raceResults = emptyMap<String, List<Pair<String, Int>>>().toMutableMap()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        var resultsParsedStrings = listOf<Pair<String, Int>>()
        resultsJson.forEach { r ->
            resultsParsedStrings = resultsParsedStrings + (
                (r.driver!!.givenName + "-" + r.driver!!.familyName)
                    to (r.points!!.toInt())
                )
        }

        raceResults += (raceIdentifier to resultsParsedStrings)
    }

    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })

    val allConcurrentNames = validConcurrents.keys.fold(setOf<String>()) { s, element -> s + element }

    var e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Pilots 2023") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -condorcetAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #2.1 CondorcetAlgorithm -> F1 Pilots 2023 in input data, for every race, pilots are ordered\n" +
            "as they arrived in race ranking",
    )
    e.printRankings()

    e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Pilots 2023") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -schultzeAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #2.2 SchultzeAlgorithm -> F1 Pilots 2023 in input data, for every race, pilots are ordered\n" +
            "as they arrived in race ranking",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}

private suspend fun fun2() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2018.json")
    println("Downloading championship data...")

    val raceResults = emptyMap<String, List<Pair<String, Int>>>().toMutableMap()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        var resultsParsedStrings = listOf<Pair<String, Int>>()
        resultsJson.forEach { r ->
            resultsParsedStrings = resultsParsedStrings + (
                (r.driver!!.givenName + "-" + r.driver!!.familyName)
                    to (r.points!!.toInt())
                )
        }

        raceResults += (raceIdentifier to resultsParsedStrings)
    }

    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })

    val allConcurrentNames = validConcurrents.keys.fold(setOf<String>()) { s, element -> s + element }

    var e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Pilots 2018 ") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -condorcetAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #2.1 CondorcetAlgorithm -> F1 Pilots 2018 in input data, for every race, pilots are ordered\n " +
            "as they arrived in race ranking ",
    )
    e.printRankings()

    e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Pilots 2018 ") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -schultzeAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #2.2 SchultzeAlgorithm -> F1 Pilots 2018 in input data, for every race, pilots are ordered\n " +
            "as they arrived in race ranking ",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}
/* private suspend fun fun3() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2018.json")
    println("Downloading championship data...")

    val raceResults = mutableMapOf<String, List<Pair<String, Float>>>()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        val resultsParsedStrings = mutableListOf<Pair<String, Float>>()
        resultsJson.forEach { r ->
            resultsParsedStrings += (
                (r.driver!!.givenName + "-" + r.driver!!.familyName)
                    to (r.fastestLap?.averageSpeed?.speed ?: -1.0f)
                )
        }

        val sortedBySpeed = resultsParsedStrings.sortedByDescending { r -> r.second }
        raceResults += (raceIdentifier to sortedBySpeed)
    }

    println(raceResults)

    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })
    println(validConcurrents)

    val e =
        PollManagerInstance<FastestLapAvgSpeed, ListOfPreferencesVote<FastestLapAvgSpeed>>() initializedAs {
            +poll {
                -competition("F1 2018") {
                    validConcurrents.forEach {
                        +competitor(it.key) {
                            it.value.forEach { v -> +(FastestLapAvgSpeed realized v) }
                        }
                    }
                }
                -condorcetAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #3 CondorcetAlgorithm -> in input data, for every race, pilots are ordered by\n" +
            "DESCENDING average speed of fastest lap)",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}*/

/*private suspend fun fun4() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2018.json")
    println("Downloading championship data...")

    val raceResults = mutableMapOf<String, List<Pair<String, Duration>>>()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        val resultsParsedStrings = mutableListOf<Pair<String, Duration>>()
        resultsJson.forEach { r ->
            resultsParsedStrings += (
                (r.driver!!.givenName + "-" + r.driver!!.familyName)
                    to (r.fastestLap?.time?.time?.parseTime() ?: Duration.INFINITE)
                )
        }

        val sortedBySpeed = resultsParsedStrings.sortedBy { r -> r.second }
        raceResults += (raceIdentifier to sortedBySpeed)
    }

    println(raceResults)

    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })
    println(validConcurrents)

    val e =
        PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("F1 2018") {
                    validConcurrents.forEach {
                        +competitor(it.key) {
                            it.value.forEach { v -> +(BestTimeInMatch realized v) }
                        }
                    }
                }
                -condorcetAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #4 CondorcetAlgorithm -> -> in input data, for every race, pilots are ordered by \n" +
            "ASCENDING time of fastest lap",
    )
    e.printRankings()

    println("Press Enter key to close")
    readln()
    httpClient.close()
}*/

private suspend fun fun5() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2023.json")
    println("Downloading championship data...")

    val raceResults = emptyMap<String, List<Pair<String, Int>>>().toMutableMap()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")

        var resultsParsedStringsPairs = listOf<Pair<String, Int>>()
        resultsJson.forEach { r ->
            resultsParsedStringsPairs = resultsParsedStringsPairs +
                ((r.constructorNode!!.name!!).replace(" ", "-") to r.points!!.toInt())
        }
        resultsParsedStringsPairs = resultsParsedStringsPairs
            .groupBy { e1 -> e1.first }
            .mapValues { (_, gruppo) -> gruppo.sumOf { e2 -> e2.second } }
            .toList()
            .sortedByDescending { p -> p.second }

        // group by constructor then sum points then order by desc
        raceResults += (raceIdentifier to resultsParsedStringsPairs)
    }

    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })

    val allConcurrentNames = validConcurrents.keys.fold(setOf<String>()) { s, element -> s + element }

    var e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Constructors 2023") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -condorcetAlgorithm {}

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #5.1 CondorcetAlgorithm -> F1 Constructors 2023, in input data, for every race,\n" +
            " constructors are ordered by DESCENDING sum of points )",
    )
    e.printRankings()

    e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Constructors 2023") {
                    allConcurrentNames.forEach {
                        +competitor(it) {
                            validConcurrents[it]!!.forEach { v -> +(PointsInRace realized v) }
                        }
                    }
                }
                -schultzeAlgorithm { }

                raceResults.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element.first } votedBy runningField)
                }
            }
        }
    println(
        "Example #5.2 SchultzeAlgorithm -> F1 Constructors 2023, in input data, for every race,\n" +
            " constructors are ordered by DESCENDING sum of points )",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}
