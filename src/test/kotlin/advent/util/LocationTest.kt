package advent.util

import kotlin.test.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class LocationTest {

    @TestFactory
    fun testLocationClosedRangeAllCorners(): List<DynamicTest> {
        val expected =
            listOf(
                Location(3, 4),
                Location(4, 4),
                Location(5, 4),
                Location(3, 5),
                Location(4, 5),
                Location(5, 5),
                Location(3, 6),
                Location(4, 6),
                Location(5, 6))
        return mapOf(
                Location(3, 4) to Location(5, 6),
                Location(3, 6) to Location(5, 4),
                Location(5, 6) to Location(3, 4),
                Location(5, 4) to Location(3, 6))
            .map { (start, end) ->
                DynamicTest.dynamicTest("From $start to $end") { assertEquals(expected, (start..end).toList()) }
            }
    }

    @TestFactory
    fun testLocationClosedRangeContains(): List<DynamicTest> {
        val range = Location(2, 2)..Location(3, 3)
        val outside =
            listOf(
                Location(1, 1),
                Location(2, 1),
                Location(3, 1),
                Location(4, 1),
                Location(1, 2),
                Location(4, 2),
                Location(1, 3),
                Location(4, 3),
                Location(1, 4),
                Location(2, 4),
                Location(3, 4),
                Location(4, 4))
        return outside.map { DynamicTest.dynamicTest("Should not contain $it") { assertFalse(it in range) } }
    }

    @TestFactory
    fun testLocationOpenEndRangeAllCorners(): List<DynamicTest> {
        val expected = listOf(Location(3, 4), Location(4, 4), Location(3, 5), Location(4, 5))
        return mapOf(
                Location(3, 4) to Location(5, 6),
                Location(3, 6) to Location(5, 4),
                Location(5, 6) to Location(3, 4),
                Location(5, 4) to Location(3, 6))
            .map { (start, end) ->
                DynamicTest.dynamicTest("From $start to $end") { assertEquals(expected, (start..<end).toList()) }
            }
    }

    @TestFactory
    fun testLocationOpenEndRangeContains(): List<DynamicTest> {
        val range = Location(2, 2)..<Location(4, 4)
        val outside =
            listOf(
                Location(1, 1),
                Location(2, 1),
                Location(3, 1),
                Location(4, 1),
                Location(1, 2),
                Location(4, 2),
                Location(1, 3),
                Location(4, 3),
                Location(1, 4),
                Location(2, 4),
                Location(3, 4),
                Location(4, 4))
        return outside.map { DynamicTest.dynamicTest("Should not contain $it") { assertFalse(it in range) } }
    }
}
