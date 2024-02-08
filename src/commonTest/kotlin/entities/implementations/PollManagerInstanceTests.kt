package entities.implementations

import entities.abstract.CompetitorAbstraction
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.SinglePreferenceVote
import entities.types.BestTimeInMatch
import entities.types.BestTimeInMatch.Companion.realized
import entities.types.ConstantParameter
import entities.types.WinsInCampionship
import entities.types.WinsInCampionship.Companion.realized
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class PollManagerInstanceTests : StringSpec({

    "Should  be thrown exception when candidates are declared more than once, in any algorithm" {

        shouldThrowWithMessage<IllegalStateException>("Candidate already declared") {
            PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("Competitor 1") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                        +competitor("Competitor 1") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                    }
                    -majorityVotesAlgorithm {}
                }
            }
        }
        shouldThrowWithMessage<IllegalStateException>("Candidate already declared") {
            PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("Competitor 1") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                        +competitor("Competitor 1") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                    }
                    -majorityVotesHScoreAlgorithm {}
                }
            }
        }
        shouldThrowWithMessage<IllegalStateException>("Candidate already declared") {
            PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("Competitor 1") {
                        }
                        +competitor("Competitor 1") {
                        }
                    }
                    -majorityVotesLScoreAlgorithm {}
                }
            }
        }
        shouldThrowWithMessage<IllegalStateException>("Candidate already declared") {
            PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("C") {
                        }
                        +competitor("C") {
                        }
                    }
                    -condorcetAlgorithm {}
                }
            }
        }
    }

    "Should be thrown exception when singlepreference vote is about a not allowed candidate" {

        shouldThrowWithMessage<NoSuchElementException>("Voted candidate doesn't exist as object") {
            PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("Competitor 1") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                        +competitor("Competitor 2") {
                            +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        }
                    }
                    -majorityVotesAlgorithm {}
                    +("a" votedBy "b")
                }
            }
        }
    }

    "Should be thrown exception when listofpreferences vote contains not allowed candidate" {
        shouldThrowWithMessage<IllegalStateException>(
            "A list of preferences contains one o more not allowed candidate",
        ) {
            PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {

                        +competitor("A") {
                        }
                        +competitor("C") {
                        }
                    }
                    -condorcetAlgorithm {}
                    +("A" then "B" votedBy "AAA")
                }
            }
        }
    }

    "Should not be thrown exception when listofpreferences vote doesn't contain every allowed candidate" {
        shouldNotThrow<IllegalStateException> {
            PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("A") {
                        }
                        +competitor("B") {
                        }
                        +competitor("C") {
                        }
                    }
                    -condorcetAlgorithm {}
                    +("A" then "B" votedBy "AAA")
                }
            }
        }

        val a = PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("A") {
                    }
                    +competitor("B") {
                    }
                    +competitor("C") {
                    }
                }
                -condorcetAlgorithm {}
                +("A" then "B" votedBy "AAA")
            }
        }

        a.pollList.first().votesList.first()
            .votedCompetitors.map { it.name }.shouldBe(listOf("A", "B", "C"))
    }

    "Should be thrown exception when listofpreferences vote contains same candidate more than once" {
        shouldThrowWithMessage<IllegalStateException>(
            "Every allowed candidate can be present only once in the list of competitors",
        ) {
            PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
                +poll {

                    -competition("Sport match") {
                        +competitor("A") {
                        }
                        +competitor("B") {
                        }
                        +competitor("C") {
                        }
                    }
                    -condorcetAlgorithm {}
                    +("A" then "B" then "B" then "C" votedBy "AAA")
                }
            }
        }
    }

    "Poll simulation should return a ranking, computed with MajorityVotesAlgorithm" {
        val a = PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("Competitor 1") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                    }
                    +competitor("Competitor 2") {
                        +(BestTimeInMatch realized (20.toDuration(DurationUnit.HOURS)))
                    }
                }
                -majorityVotesAlgorithm {
                    +ConstantParameter.AllowMultipleVoteInPollParameter
                }

                +("Competitor 2" votedBy "J")
                +("Competitor 2" votedBy "F")
                +("Competitor 1" votedBy "J")
                +("Competitor 1" votedBy "F")
            }
        }

        val rankings = a.computeAllPolls()

        rankings shouldHaveSize 1
        rankings.first().ranking shouldHaveSize 1
        val entry = rankings.first().ranking.entries.first()

        entry.value shouldBe 2

        entry.key shouldHaveSize 2

        val competitor1 = object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
            this.name = "Competitor 1"
            this.scores = listOf(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
        }
        val competitor2 = object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
            this.name = "Competitor 2"
            this.scores = listOf(BestTimeInMatch realized (20.toDuration(DurationUnit.HOURS)))
        }
        entry.key.map { it.name }.shouldContainAll(competitor2.name, competitor1.name)

        shouldNotThrowAny { a.printRankings() }
    }

    "Poll simulation should return a ranking, computed with MajorityVotesAndHighestScoreAlgorithm" {
        val a = PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("Competitor 1") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                    }
                    +competitor("Competitor 2") {
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
                        +(BestTimeInMatch realized (20.toDuration(DurationUnit.DAYS)))
                    }
                    +competitor("Competitor 3") {
                        +(BestTimeInMatch realized (20.toDuration(DurationUnit.DAYS)))
                    }
                }
                -majorityVotesHScoreAlgorithm {
                }

                +("Competitor 2" votedBy "anonym1")
                +("Competitor 2" votedBy "anonym2")
                +("Competitor 1" votedBy "anonym3")
                +("Competitor 1" votedBy "anonym4")
                +("Competitor 3" votedBy "anonym5")
                +("Competitor 3" votedBy "anonym6")
            }
        }

        val ranking = a.computeAllPolls()

        ranking shouldHaveSize 1

        ranking.first().ranking shouldHaveSize 2

        val entries = ranking.first().ranking.entries.take(2)
        entries.map { it.value } shouldContainAll (listOf(2))
        entries[0].key.size shouldBe 2
        entries[1].key.size shouldBe 1

        val competitor1 = object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
            this.name = "Competitor 1"
            this.scores = listOf(BestTimeInMatch realized (1.toDuration(DurationUnit.DAYS)))
        }
        val competitor2 = object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
            this.name = "Competitor 2"
            this.scores = listOf(BestTimeInMatch realized (20.toDuration(DurationUnit.DAYS)))
        }
        val competitor3 = object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
            this.name = "Competitor 3"
            this.scores = listOf(BestTimeInMatch realized (20.toDuration(DurationUnit.DAYS)))
        }
        entries[0].key.map { it.name }.shouldContainAll(competitor2.name, competitor3.name)
        entries[1].key.map { it.name }.shouldContainAll(competitor1.name)

        entries[0].key.map { it.scores.first().scoreValue }.shouldContainAll(
            competitor2.scores.first().scoreValue,
            competitor3.scores.first().scoreValue,
        )
        entries[1].key.map { it.scores.first().scoreValue }.shouldContainAll(competitor1.scores.first().scoreValue)
    }

    "Poll simulation should return a ranking, computed with MajorityVotesAndLowestScoreAlgorithm" {
        val a = PollManagerInstance<WinsInCampionship, SinglePreferenceVote<WinsInCampionship>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("Competitor 1") {
                        +(WinsInCampionship realized 20)
                    }
                    +competitor("Competitor 2") {
                        +(WinsInCampionship realized 1)
                        +(WinsInCampionship realized 20)
                    }
                    +competitor("Competitor 3") {
                        +(WinsInCampionship realized 1)
                    }
                }
                -majorityVotesLScoreAlgorithm {
                }

                +("Competitor 2" votedBy "anonym1")
                +("Competitor 2" votedBy "anonym2")
                +("Competitor 1" votedBy "anonym3")
                +("Competitor 1" votedBy "anonym4")
                +("Competitor 3" votedBy "anonym5")
                +("Competitor 3" votedBy "anonym6")
            }
        }

        val ranking = a.computeAllPolls()

        ranking shouldHaveSize 1
        ranking.first().ranking shouldHaveSize 2

        val entries = ranking.first().ranking.entries.take(2)
        entries.map { it.value } shouldContainAll (listOf(2))
        entries[0].key.size shouldBe 2
        entries[1].key.size shouldBe 1

        val competitor1 = object : CompetitorAbstraction<WinsInCampionship>() {}.apply {
            this.name = "Competitor 1"
            this.scores = listOf((WinsInCampionship realized 20))
        }
        val competitor2 = object : CompetitorAbstraction<WinsInCampionship>() {}.apply {
            this.name = "Competitor 2"
            this.scores = listOf((WinsInCampionship realized 1))
        }
        val competitor3 = object : CompetitorAbstraction<WinsInCampionship>() {}.apply {
            this.name = "Competitor 3"
            this.scores = listOf((WinsInCampionship realized 1))
        }

        entries[0].key.map { it.name }.shouldContainAll(competitor2.name, competitor3.name)
        entries[1].key.map { it.name }.shouldContainAll(competitor1.name)

        entries[0].key.map { it.scores.first().scoreValue }.shouldContainAll(
            competitor2.scores.first().scoreValue,
            competitor3.scores.first().scoreValue,
        )
        entries[1].key.map { it.scores.first().scoreValue }.shouldContainAll(competitor1.scores.first().scoreValue)
    }

    "Poll simulation should return a ranking, computed with CondorcetAlgorithm" {
        val a = PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("competitorA") {
                    }

                    +competitor("competitorB") {
                    }

                    +competitor("competitorC") {
                    }
                }
                -condorcetAlgorithm {}

                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorA" then "competitorB").asAnonymousVote())
                +(("competitorC" then "competitorA" then "competitorB").asAnonymousVote())
            }
        }

        val rankings = a.computeAllPolls()

        rankings shouldHaveSize 1

        val ranking = rankings.first().ranking
        ranking shouldHaveSize 3
        ranking.values shouldContainAll setOf(null)

        ranking.keys.toList() shouldBe listOf(
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorC"
                    this.scores = listOf()
                },
            ),
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorB"
                    this.scores = listOf()
                },
            ),
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorA"
                    this.scores = listOf()
                },
            ),
        )
    }

    "Poll simulation should return a ranking, computed with SchultzeAlgorithm" {
        val a = PollManagerInstance<BestTimeInMatch, ListOfPreferencesVote<BestTimeInMatch>>() initializedAs {
            +poll {

                -competition("Sport match") {
                    +competitor("competitorA") {
                    }

                    +competitor("competitorB") {
                    }

                    +competitor("competitorC") {
                    }
                }
                -schultzeAlgorithm {}

                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorA" then "competitorC" then "competitorB").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorB" then "competitorC" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorB" then "competitorA").asAnonymousVote())
                +(("competitorC" then "competitorA" then "competitorB").asAnonymousVote())
                +(("competitorC" then "competitorA" then "competitorB").asAnonymousVote())
            }
        }

        val rankings = a.computeAllPolls()

        rankings shouldHaveSize 1

        val ranking = rankings.first().ranking
        ranking shouldHaveSize 3
        ranking.values shouldContainAll setOf(null)

        ranking.keys.toList() shouldBe listOf(
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorC"
                    this.scores = listOf()
                },
            ),
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorB"
                    this.scores = listOf()
                },
            ),
            setOf(
                object : CompetitorAbstraction<BestTimeInMatch>() {}.apply {
                    this.name = "competitorA"
                    this.scores = listOf()
                },
            ),
        )
    }
})
