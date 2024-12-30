package advent._2015

import advent.Day
import advent.util.Grid
import advent.util.Location
import java.time.LocalDate
import kotlin.math.max

const val TURN_ON = "turn on"
const val TURN_OFF = "turn off"
const val TOGGLE = "toggle"

class Day6 : Day(LocalDate.of(2015, 12, 6)) {

    private val regex: Regex = "($TURN_ON|$TURN_OFF|$TOGGLE) (\\d+),(\\d+) through (\\d+),(\\d+)".toRegex()

    override fun part1(input: String): String {
        val grid: Grid<Int> = Grid(1000, 1000, 0)

        input.lines().forEach { line ->
            val found = regex.find(line)!!
            val from = Location(found.groups[2]!!.value.toInt(), found.groups[3]!!.value.toInt())
            val to = Location(found.groups[4]!!.value.toInt(), found.groups[5]!!.value.toInt())

            when (found.groups[1]!!.value) {
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
            val found = regex.find(line)!!
            val from = Location(found.groups[2]!!.value.toInt(), found.groups[3]!!.value.toInt())
            val to = Location(found.groups[4]!!.value.toInt(), found.groups[5]!!.value.toInt())

            when (found.groups[1]!!.value) {
                TURN_ON -> grid.replace(from..to) { it + 1 }
                TURN_OFF -> grid.replace(from..to) { max(0, it - 1) }
                TOGGLE -> grid.replace(from..to) { it + 2 }
            }
        }

        return grid.sum().toString()
    }
}
