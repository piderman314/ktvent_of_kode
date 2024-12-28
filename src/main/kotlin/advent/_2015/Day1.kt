package advent._2015

import advent.Day
import java.time.LocalDate

class Day1 : Day(LocalDate.of(2015, 12, 1)) {
    override fun part1(input: String): String {
        return input.toCharArray().sumOf { ch ->
            when (ch) {
                '(' -> 1L
                ')' -> -1L
                else -> 0L
            }
        }.toString()
    }

    override fun part2(input: String): String {
        TODO("Not yet implemented")
    }
}