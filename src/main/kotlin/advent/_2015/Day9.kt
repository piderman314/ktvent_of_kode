package advent._2015

import advent.Day
import java.time.LocalDate
import kotlin.math.max
import kotlin.math.min

class Day9 : Day(LocalDate.of(2015, 12, 9)) {

    val regex = "([A-Za-z]*) to ([A-Za-z]*) = ([0-9]*)".toRegex()

    override fun part1(input: String): String {
        val cities = mutableSetOf<String>()
        val distances = mutableMapOf<Pair<String, String>, Int>()

        for (line in input.lines()) {
            val result = regex.matchEntire(line) ?: throw IllegalStateException("Could not parse $line")

            val city1 = result.groups[1]!!.value
            val city2 = result.groups[2]!!.value
            val distance = result.groups[3]!!.value.toInt()

            cities.add(city1)
            cities.add(city2)
            distances[city1 to city2] = distance
            distances[city2 to city1] = distance
        }

        val cityCombinations = allCombinations(cities)

        var minimum = Int.MAX_VALUE
        for (combination in cityCombinations) {
            val distance = combination.windowed(2).sumOf { (c1, c2) -> distances[c1 to c2]!! }
            minimum = min(minimum, distance)
        }

        return minimum.toString()
    }

    override fun part2(input: String): String {
        val cities = mutableSetOf<String>()
        val distances = mutableMapOf<Pair<String, String>, Int>()

        for (line in input.lines()) {
            val result = regex.matchEntire(line) ?: throw IllegalStateException("Could not parse $line")

            val city1 = result.groups[1]!!.value
            val city2 = result.groups[2]!!.value
            val distance = result.groups[3]!!.value.toInt()

            cities.add(city1)
            cities.add(city2)
            distances[city1 to city2] = distance
            distances[city2 to city1] = distance
        }

        val cityCombinations = allCombinations(cities)

        var maximum = 0
        for (combination in cityCombinations) {
            val distance = combination.windowed(2).sumOf { (c1, c2) -> distances[c1 to c2]!! }
            maximum = max(maximum, distance)
        }

        return maximum.toString()
    }

    private fun allCombinations(input: Collection<String>): List<List<String>> {
        if (input.isEmpty()) {
            return listOf()
        }

        if (input.size == 1) {
            return listOf(mutableListOf(input.first()))
        }

        return input.map { item -> allCombinations(input.filter { it != item }).map { it + listOf(item) } }.flatten()
    }
}
