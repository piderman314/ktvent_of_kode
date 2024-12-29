package advent._2015

import advent.Day
import java.time.LocalDate

class Day1 : Day(LocalDate.of(2015, 12, 1)) {
    override fun part1(input: String): String {
        return input.toCharArray().sumOf(::braceToAddend).toString()
    }

    override fun part2(input: String): String {
        val fold =
            input.toCharArray().runningFoldIndexed(-1 to 0L) { index, acc, ch ->
                (index + 1) to (acc.second + braceToAddend(ch))
            }
        return fold.find { it.second == -1L }!!.first.toString()
    }

    private fun braceToAddend(c: Char) =
        when (c) {
            '(' -> 1L
            ')' -> -1L
            else -> 0L
        }
}
