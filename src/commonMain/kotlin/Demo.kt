
import entities.implementations.PollManagerInstance
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.SinglePreferenceVote
import entities.types.BestTimeInMatch
import entities.types.BestTimeInMatch.Companion.realized
import entities.types.ConstantParameter
import entities.types.FastestLapAvgSpeed
import entities.types.FastestLapAvgSpeed.Companion.realized
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlin.time.Duration
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

private fun String.parseTime(): Duration {
    val parts = this.split(":")
    val minutes = parts[0].toLong()
    val secondsAndMilliseconds = parts[1].split(".")
    val seconds = secondsAndMilliseconds[0].toLong()
    val milliseconds = secondsAndMilliseconds[1].toLong()
    return minutes.toDuration(DurationUnit.MINUTES) +
        seconds.toDuration(DurationUnit.SECONDS) +
        milliseconds.toDuration(DurationUnit.MILLISECONDS)
}

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

                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym" + counter++)
            } // competitorC, competitorB, competitorA
        }
    println("Example #1 CondorcetAlgorithm -> Condorcet result is competitorC - competitorB - competitorA")
    d.printRankings()

    fun2()
    fun3()
    fun4()
}

/* private suspend fun fun1() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2023.json")
    println("Downloading championship data...")

    val raceResults = emptyMap<String, List<String>>().toMutableMap()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!
        // println(resultsJson)

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        val resultsParsedStrings = mutableListOf<String>()
        resultsJson.forEach { r ->
            resultsParsedStrings += r.driver!!.givenName + "-" + r.driver!!.familyName // + "-" + r.position
        }

        raceResults += (raceIdentifier to resultsParsedStrings)
    }

    println(raceResults)

    /* val s = mutableListOf<String>()
  for((_, value) in raceResults){
      s += value
  }

  val alwaysPresentCounter = (s.groupingBy { it }.eachCount().toMap()).map { it.value }.max()
  val alwaysPresentConcurrent = (s.groupingBy { it }.eachCount().toMap())
  .filter { it.value == alwaysPresentCounter }.keys
  var validConcurrents = raceResults.mapValues { it.value.filter { it in alwaysPresentConcurrent } }
 */
    val validConcurrents = raceResults
    println(validConcurrents)

    val allConcurrents = validConcurrents.values.fold(setOf<String>()) { s, element -> s + element.toSet() }
    val e =
        PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("F1 2023") {
                    allConcurrents.forEach {
                        +competitor(it) {
                        }
                    }
                }
                -condorcetAlgorithm {}

                validConcurrents.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element } votedBy runningField)
                }
            }
        }
    println("Example #2 CondorcetAlgorithm -> ")
    e.printRankings()

    println("Press Enter key to close")
    readln()
    httpClient.close()
}*/

private suspend fun fun2() {
    val httpClient = HttpClient()

    var response: HttpResponse = httpClient.get("https://ergast.com/api/f1/2018.json")
    println("Downloading championship data...")

    val raceResults = emptyMap<String, List<String>>().toMutableMap()

    val root = json.decodeFromString<RootType>(response.bodyAsText())
    val listOfRaces = root.mRData!!.raceTable!!.races!!
    listOfRaces.forEach {
        println("Downloading race data...")
        response = httpClient.get("https://ergast.com/api/f1/${it.season}/${it.round}/results.json")

        val resultsJson =
            json
                .decodeFromString<RootType>(response.bodyAsText()).mRData!!.raceTable!!
                .races!![0].results!!
        // println(resultsJson)

        val raceIdentifier = (it.raceName + "-" + it.round + "-" + it.season).replace(" ", "-")
        val resultsParsedStrings = mutableListOf<String>()
        resultsJson.forEach { r ->
            resultsParsedStrings += r.driver!!.givenName + "-" + r.driver!!.familyName // + "-" + r.position
        }

        raceResults += (raceIdentifier to resultsParsedStrings)
    }

    println(raceResults)

    /* val s = mutableListOf<String>()
  for((_, value) in raceResults){
      s += value
  }

  val alwaysPresentCounter = (s.groupingBy { it }.eachCount().toMap()).map { it.value }.max()
  val alwaysPresentConcurrent = (s.groupingBy { it }.eachCount().toMap())
  .filter { it.value == alwaysPresentCounter }.keys
  var validConcurrents = raceResults.mapValues { it.value.filter { it in alwaysPresentConcurrent } }
     */
    val validConcurrents = raceResults
    println(validConcurrents)

    val allConcurrents = validConcurrents.values.fold(setOf<String>()) { s, element -> s + element.toSet() }
    val e =
        PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition("F1 2018") {
                    allConcurrents.forEach {
                        +competitor(it) {
                        }
                    }
                }
                -condorcetAlgorithm {}

                validConcurrents.entries.forEach { (runningField, competitors) ->
                    +(competitors.fold(listOf<String>()) { l, element -> l then element } votedBy runningField)
                }
            }
        }
    println("Example #2 CondorcetAlgorithm -> ")
    e.printRankings()

    println("Press Enter key to continue")
    readln()
    httpClient.close()
}
private suspend fun fun3() {
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
        // println(resultsJson)

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
    println("Example #3 CondorcetAlgorithm -> ")
    e.printRankings()

    println("Press Enter key to close")
    readln()
    httpClient.close()
}

private suspend fun fun4() {
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
        // println(resultsJson)

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
    println("Example #4 CondorcetAlgorithm -> ")
    e.printRankings()

    println("Press Enter key to close")
    readln()
    httpClient.close()
}
