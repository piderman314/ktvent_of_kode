package advent._2015

import advent.Day
import advent.util.Grid
import advent.util.Location
import java.time.LocalDate
import java.util.regex.Pattern
import kotlin.math.max

const val TURN_ON = "turn on"
const val TURN_OFF = "turn off"
const val TOGGLE = "toggle"

class Day6 : Day(LocalDate.of(2015, 12, 6)) {

    val pattern: Pattern = Pattern.compile("($TURN_ON|$TURN_OFF|$TOGGLE) (\\d+),(\\d+) through (\\d+),(\\d+)")

    override fun part1(input: String): String {
        val grid: Grid<Int> = Grid(1000, 1000, 0)

        input.lines().forEach { line ->
            val matcher = pattern.matcher(line)
            matcher.find()
            val from = Location(matcher.group(2).toInt(), matcher.group(3).toInt())
            val to = Location(matcher.group(4).toInt(), matcher.group(5).toInt())

            when (matcher.group(1)) {
                TURN_ON -> grid[from..to] = 1
                TURN_OFF -> grid[from..to] = 0
                TOGGLE -> grid.replace(from..to) { it xor 1 }
            }
        }

        return grid.sum().toString()
    }

    override fun part2(input: String): String {
        val grid: Grid<Int> = Grid(1000, 1000, 0)

        input.lines().forEach { line ->
            val matcher = pattern.matcher(line)
            matcher.find()
            val from = Location(matcher.group(2).toInt(), matcher.group(3).toInt())
            val to = Location(matcher.group(4).toInt(), matcher.group(5).toInt())

            when (matcher.group(1)) {
                TURN_ON -> grid.replace(from..to) { it + 1 }
                TURN_OFF -> grid.replace(from..to) { max(0, it - 1) }
                TOGGLE -> grid.replace(from..to) { it + 2 }
            }
        }

        return grid.sum().toString()
    }
}
