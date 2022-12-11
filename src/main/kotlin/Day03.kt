object Day03 {

    private fun getPriority(char: Char): Int =
        (('a'..'z') + ('A'..'Z')).indexOf(char) + 1

    private fun String.letters(): List<Char> =
        this.split("")
            .filter { it.isNotBlank() }
            .map { it[0] }

    fun solveRucksack(): Int =
        input.split("\n")
            .sumOf { rawSack ->
                val left = rawSack.substring(0, rawSack.length / 2).letters()
                val right = rawSack.substring(rawSack.length / 2, rawSack.length).letters()

                val commonChar = left.find { right.indexOf(it) > -1 }!!

                getPriority(commonChar)
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
