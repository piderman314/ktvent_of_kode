package advent._2015

import advent.Day
import advent.util.RadixConverter
import java.time.LocalDate

class Day11 : Day(LocalDate.of(2015, 12, 11)) {

    val converter = RadixConverter("abcdefghijklmnopqrstuvwxyz")

    override fun part1(input: String): String {
        var inputLong = converter.toLong(input)

        while (true) {
            val string = converter.toString(inputLong)
            if (string.isNice2()) {
                return string
            }

            inputLong++
        }
    }

    override fun part2(input: String): String {
        return part1(converter.toString(converter.toLong(part1(input)) + 1L))
    }
}

private fun String.isNice2(): Boolean {
    if (!windowed(3).any { it[0] + 1 == it[1] && it[0] + 2 == it[2] }) {
        return false
    }

    if (this.contains("[iol]".toRegex())) {
        return false
    }

    return windowed(2).mapNotNull { if (it[0] == it[1]) it[0] else null }.toSet().size >= 2
}
