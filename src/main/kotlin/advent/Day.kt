package advent

import java.time.LocalDate

abstract class Day(val date: LocalDate) {

    val realInputFile: String
        get() = "/real_input/${date.year}/day${date.dayOfMonth}.txt"

    abstract fun part1(input: String): String

    abstract fun part2(input: String): String
}
