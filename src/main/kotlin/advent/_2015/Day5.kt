package advent._2015

import advent.Day
import java.time.LocalDate

private val VOWELS = listOf('a', 'e', 'i', 'o', 'u')
private val FORBIDDEN = listOf("ab", "cd", "pq", "xy")

class Day5 : Day(LocalDate.of(2015, 12, 5)) {
    override fun part1(input: String): String = input.lines().count { it.isNice1() }.toString()

    override fun part2(input: String): String {
        return input.lines().count { it.isNice2() }.toString()
    }

}

private fun String.isNice1(): Boolean {
    if (this.toCharArray().count { VOWELS.contains(it) } < 3) {
        return false
    }

    if (FORBIDDEN.any { this.contains(it) }) {
        return false
    }

    val folded = this.fold(Pair(' ', false)) { pair, char -> Pair(char, pair.second || char == pair.first) }
    return folded.second
}

private fun String.isNice2(): Boolean {
    val m = (0..(this.length - 2))
        .map { this.substring(it, it + 2) to it }
        .groupBy { it.first }
    if (m.values.map { pairList -> pairList.map { it.second } }.none { it.isNice() }) {
        return false
    }

    val folded = this.fold(Pair("  ", false)) { pair, char ->
        Pair(
            (pair.first + char).substring(1),
            pair.second || char == pair.first[0]
        )
    }
    return folded.second
}

private fun List<Int>.isNice(): Boolean {
    if (size < 2) {
        return false
    }

    val sorted = sorted()
    return sorted.drop(1)
        .fold(sorted[0] to false) { pair, value -> value to (pair.second || pair.first + 1 != value) }.second
}