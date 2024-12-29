package advent._2015

import advent.Day
import java.time.LocalDate

class Day2 : Day(LocalDate.of(2015, 12, 2)) {
    override fun part1(input: String): String {
        return input.lines().sumOf(::dimensionsToWrappingPaper).toString()
    }

    override fun part2(input: String): String {
        return input.lines().sumOf(::dimensionsToBowLength).toString()
    }

    private fun dimensionsToWrappingPaper(dimensions: String): Long {
        val (l, w, h) = dimensions.split("x").map { it.toLong() }
        val areas = listOf(l * w, w * h, h * l)
        return 2 * areas.sum() + areas.min()
    }

    private fun dimensionsToBowLength(dimensions: String): Long {
        val (l, w, h) = dimensions.split("x").map { it.toLong() }
        val perimeters = listOf(2 * (l + w), 2 * (w + h), 2 * (h + l))
        return perimeters.min() + (l * w * h)
    }
}
