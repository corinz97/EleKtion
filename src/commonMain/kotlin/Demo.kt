
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
private const val YEAR2023 = 2023
private const val YEAR2018 = 2018

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
    val year = YEAR2023 // 2023
    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/$year.json")
    println("Downloading championship data...")

    var raceResults = emptyMap<String, List<Pair<String, Int>>>()

    var root = json.decodeFromString<RootType>(response.bodyAsText())
    var listOfRaces = root.mRData!!.raceTable!!.races!!
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
                    to (r.position!!.toInt())
                )
        }

        raceResults = raceResults + (raceIdentifier to resultsParsedStrings)
    }

    // here i have all concurrents of all GPs
    val validConcurrents = raceResults.flatMap { it.value }.groupBy({ it.first }, { it.second })

    val allConcurrentNames = validConcurrents.keys.fold(setOf<String>()) { s, element -> s + element }

    var election =
        PollManagerInstance<Nothing, ListOfPreferencesVote<Nothing>>() initializedAs {
            var currentGPs: Map<String, List<Pair<String, Int>>> = mapOf()
            var index = 1
            for (raceResult in raceResults) {
                currentGPs = currentGPs + raceResult.toPair()
                +poll {
                    -competition("F1 Pilots $year - Partial Ranking - GPs #1 to #${index++}") {
                        allConcurrentNames.forEach {
                            +competitor(it) {}
                        }
                    }
                    -condorcetAlgorithm {}
                    // incremental set of votes
                    currentGPs.entries.forEach { (runningField, competitors) ->
                        +(
                            competitors.fold(listOf<String>()) { l, element -> l then element.first }
                                votedBy runningField
                            )
                    }
                }
            }
        }

    var rankings = election.computeAllPolls()

    val flattenedOrdersInRankings = rankings.map { it.ranking.flatMap { it.key.map { it.name } } }
    val condorcetFlattenedOrderInFinalRanking = flattenedOrdersInRankings.last()

    var m: Map<String, List<Int>> = mapOf()
    for (competitor in condorcetFlattenedOrderInFinalRanking) {
        var listOfPositionsPerCompetitor: List<Int> = listOf()
        for (ranking in rankings.map { it.ranking.keys.toList() }) {
            var index = ranking.indexOfFirst { it.map { it.name }.contains(competitor) }

            listOfPositionsPerCompetitor = listOfPositionsPerCompetitor + ++index
        }
        m = m + (competitor to listOfPositionsPerCompetitor)
    }

    println(m)
    println(m.keys.joinToString(",", "[", "]") { "\"${it}\"" })
    println(m.values)

    println(
        "Example #fun1.1 CondorcetAlgorithm -> F1 Pilots $year in input data, for every race, pilots are ordered\n" +
            "as they arrived in race ranking",
    )

    election =
        PollManagerInstance<Nothing, ListOfPreferencesVote<Nothing>>() initializedAs {
            var currentGPs: Map<String, List<Pair<String, Int>>> = mapOf()
            var index = 1
            for (raceResult in raceResults) {
                currentGPs = currentGPs + raceResult.toPair()
                +poll {
                    -competition("F1 Pilots $year - Partial Ranking - GPs #1 to #${index++}") {
                        allConcurrentNames.forEach {
                            +competitor(it) {}
                        }
                    }
                    -schultzeAlgorithm {}
                    // incremental set of votes
                    currentGPs.entries.forEach { (runningField, competitors) ->
                        +(
                            competitors.fold(listOf<String>()) { l, element -> l then element.first }
                                votedBy runningField
                            )
                    }
                }
            }
        }

    rankings = election.computeAllPolls()

    val schultzeFlattenedOrderInFinalRanking = condorcetFlattenedOrderInFinalRanking
    m = mapOf()
    for (competitor in schultzeFlattenedOrderInFinalRanking) {
        var listOfPositionsPerCompetitor: List<Int> = listOf()
        for (ranking in rankings.map { it.ranking.keys.toList() }) {
            var index = ranking.indexOfFirst { it.map { it.name }.contains(competitor) }

            listOfPositionsPerCompetitor = listOfPositionsPerCompetitor + ++index
        }
        m = m + (competitor to listOfPositionsPerCompetitor)
    }

    println(m)
    println(m.keys.joinToString(",", "[", "]") { "\"${it}\"" })
    println(m.values)

    println(
        "Example #fun1.2 SchultzeAlgorithm -> F1 Pilots $year in input data, for every race, pilots are ordered\n" +
            "as they arrived in race ranking",
    )

    fun12(condorcetFlattenedOrderInFinalRanking)
}

private suspend fun fun12(condorcetFlattenedOrderInFinalRanking: List<String>) {
    val httpClient = HttpClient()
    val year = YEAR2023 // 2023
    var response = httpClient.get("https://ergast.com/api/f1/$year.json")
    println("Downloading championship data...")

    var raceResults = emptyMap<String, List<Pair<String, Int>>>()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading standings data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/driverStandings.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.standingsTable!!
                .standingsLists!![0].driverStandings!!

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        var resultsParsedStrings = listOf<Pair<String, Int>>()
        resultsJson.forEach { r ->
            resultsParsedStrings = resultsParsedStrings + (
                (r.driver!!.givenName + "-" + r.driver!!.familyName)
                    to (r.position)
                )
        }

        raceResults = raceResults + (raceIdentifier to resultsParsedStrings)
    }

    val realWorldFlattenedOrderInFinalRanking = condorcetFlattenedOrderInFinalRanking
    var m: Map<String, List<Int>> = mapOf()
    for (competitor in realWorldFlattenedOrderInFinalRanking) {
        var listOfPositionsPerCompetitor: List<Int> = listOf()

        for (ranking in raceResults.map { it.value }) {
            val index = ranking.firstOrNull { it.first == competitor }?.second ?: ranking.maxOf { it.second }

            listOfPositionsPerCompetitor = listOfPositionsPerCompetitor + index
        }
        m = m + (competitor to listOfPositionsPerCompetitor)
    }

    println(m)
    println(m.keys.joinToString(",", "[", "]") { "\"${it}\"" })
    println(m.values)

    println("Real arrivals ordered by condorcet arriving")

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}
private suspend fun fun2() {
    val httpClient = HttpClient()
    val year = YEAR2018 // 2018
    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/$year.json")
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
                -competition("F1 Pilots $year ") {
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
        "Example #fun2.1 CondorcetAlgorithm -> F1 Pilots $year in input data, for every race, pilots are ordered\n " +
            "as they arrived in race ranking ",
    )
    e.printRankings()

    e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Pilots $year ") {
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
        "Example #fun2.2 SchultzeAlgorithm -> F1 Pilots $year in input data, for every race, pilots are ordered\n " +
            "as they arrived in race ranking ",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}

private suspend fun fun5() {
    val httpClient = HttpClient()
    val year = YEAR2023 // 2023
    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/$year.json")
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
                -competition("F1 Constructors $year") {
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
        "Example #fun5.1 CondorcetAlgorithm -> F1 Constructors $year, in input data, for every race,\n" +
            " constructors are ordered by DESCENDING sum of points )",
    )
    e.printRankings()

    e =
        PollManagerInstance<PointsInRace, ListOfPreferencesVote<PointsInRace>>() initializedAs {
            +poll {
                -competition("F1 Constructors $year") {
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
        "Example #fun5.2 SchultzeAlgorithm -> F1 Constructors $year, in input data, for every race,\n" +
            " constructors are ordered by DESCENDING sum of points )",
    )
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}
