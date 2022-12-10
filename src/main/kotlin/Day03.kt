object Day03 {

    private fun getPriority(char: Char): Int =
        (('a'..'z') + ('A'..'Z')).indexOf(char) + 1

    fun solveRucksack() {
        val ruckSacks: List<Pair<String, String>> = input
            .split("\n")
            .map { rawSack ->
                Pair(
                    rawSack.substring(0, rawSack.length / 2),
                    rawSack.substring(rawSack.length / 2, rawSack.length),
                )
            }

        val prioritySum: Int = ruckSacks.map { sack ->
            val (left, right) = sack
            var priority = 0

            val leftCompartment: List<String> = left.split("").filter { it.isNotBlank() }
            val rightCompartment: List<String> = right.split("").filter { it.isNotBlank() }

            leftCompartment.forEach { str ->
                val commonLetterIdx = rightCompartment.indexOf(str)
                if (commonLetterIdx > -1) {
                    priority = getPriority(str.toCharArray()[0])
                    return@forEach
                }
            }

            return@map priority
        }.sum()

        println("priorities = $prioritySum")
    }

    private val input = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()
}
