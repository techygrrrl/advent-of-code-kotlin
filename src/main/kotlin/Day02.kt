object Day02 {
    // Points you earn for your move
    private val movePointMap = mapOf(
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
    )

    private val scoreTree = mapOf(
        // Opponent -> You -> score
        "A" to mapOf(
            "X" to 3, // rock (them) vs. rock (you)
            "Y" to 6, // rock (them) vs. paper (you)
            "Z" to 0, // rock (them) vs. scissors (you)
        ),
        "B" to mapOf(
            "X" to 0, // paper (them) vs. rock (you)
            "Y" to 3, // paper (them) vs. paper (you)
            "Z" to 6, // paper (them) vs. scissors (you)
        ),
        "C" to mapOf(
            "X" to 6, // scissors (them) vs. rock (you)
            "Y" to 0, // scissors (them) vs. paper (you)
            "Z" to 3, // scissors (them) vs. scissors (you)
        ),
    )

    fun rockPaperScissorsCheatSheet(): Int {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()

        val rounds = input.split("\n")
        val moves = rounds.map { it.split(" ") }

//        println(rounds)
//        println(moves)

        val answer = moves
            .map { move ->
                val them = move[0]
                val you = move[1]

                val score = evaluateScore(you, them)
//                println("Them = $them, you = $you, score = $score")

                score
            }
            .reduce { acc, curr -> acc + curr }

        return answer
    }

    private fun evaluateScore(yours: String, theirs: String): Int {
        val baseScore: Int = movePointMap[yours]!!

        val attackScore = scoreTree[theirs]!![yours]!!

        return baseScore + attackScore
    }
}