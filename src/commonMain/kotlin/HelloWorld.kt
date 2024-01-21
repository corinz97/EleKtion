
import entities.implementations.PollManagerInstance
import entities.interfaces.ListOfPreferencesVote
import entities.interfaces.SinglePreferenceVote
import entities.types.BestTimeInMatch
import entities.types.BestTimeInMatch.Companion.realized
import entities.types.ConstantParameter
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private const val T = 0.1

/**
 * Main fun.
 */
fun main() {
    val a =
        PollManagerInstance<BestTimeInMatch, SinglePreferenceVote<BestTimeInMatch>>() initializedAs {
            +poll {
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Race"
                    +competitor {
                        -"competitor1"
                        +(BestTimeInMatch realized (T.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (1.toDuration(DurationUnit.HOURS)))
                        +(BestTimeInMatch realized (2.toDuration(DurationUnit.HOURS)))
                    }

                    +competitor {
                        -"competitor2"
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
                -competition {
                    -"Sport match"
                    +competitor {
                        -"competitorB"
                    }

                    +competitor {
                        -"competitorA"
                    }

                    +competitor {
                        -"competitorC"
                    }
                }
                -condorcetAlgorithm {}

                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorA" then "competitorC" then "competitorB" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorB" then "competitorC" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorB" then "competitorA" votedBy "anonym"+counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym"+counter++)
                +("competitorC" then "competitorA" then "competitorB" votedBy "anonym"+counter++)
            } // competitorC, competitorB, competitorA
        }
    println("Example #1 CondorcetAlgorithm -> Condorcet result is competitorC - competitorB - competitorA")
    d.printRankings()

    println()
    println("Press Enter key to close")
    readln()
}
