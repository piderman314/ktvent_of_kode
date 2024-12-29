package advent._2015

import advent.Day
import advent.util.Location
import advent.util.toDirection
import java.time.LocalDate

class Day3 : Day(LocalDate.of(2015, 12, 3)) {
    override fun part1(input: String): String {
        return input
            .toCharArray()
            .map(Char::toDirection)
            .runningFold(Location(0, 0)) { location, direction -> location move direction }
            .toSet()
            .size
            .toString()
    }

    override fun part2(input: String): String {
        return input
            .toCharArray()
            .map(Char::toDirection)
            .runningFoldIndexed(Pair(Location(0, 0), Location(0, 0))) { index, locations, direction ->
                if (index % 2 == 0) {
                    Pair(locations.first move direction, locations.second)
                } else {
                    Pair(locations.first, locations.second move direction)
                }
            }
            .flatMap { it.toList() }
            .toSet()
            .size
            .toString()
    }
}
