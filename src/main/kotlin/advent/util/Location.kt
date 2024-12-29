package advent.util

import kotlin.math.max
import kotlin.math.min

data class Location(val x: Int, val y: Int) {

    // Operators
    operator fun rangeTo(other: Location) = LocationRange(this, other, true)

    operator fun rangeUntil(other: Location) = LocationRange(this, other, false)

    // Infix operators
    infix fun move(direction: Direction) = this move direction.distance

    infix fun move(distance: Distance) = Location(x + distance.dx, y + distance.dy)
}

class LocationRange(from: Location, toInclusive: Location, inclusive: Boolean) : Iterable<Location> {
    private val offset = if (inclusive) 0 else 1

    private val start: Location = Location(min(from.x, toInclusive.x), min(from.y, toInclusive.y))
    private val endInclusive: Location =
        Location(max(from.x, toInclusive.x) - offset, max(from.y, toInclusive.y) - offset)

    operator fun contains(location: Location): Boolean =
        location.x >= start.x && location.y >= start.y && location.x <= endInclusive.x && location.y <= endInclusive.y

    override fun iterator(): Iterator<Location> =
        object : Iterator<Location> {
            var current = start

            override fun hasNext(): Boolean = current.x <= endInclusive.x && current.y <= endInclusive.y

            override fun next(): Location {
                if (!hasNext()) {
                    throw IllegalStateException()
                }

                val result = current
                current =
                    if (result.x == endInclusive.x) {
                        Location(start.x, result.y + 1)
                    } else {
                        Location(result.x + 1, result.y)
                    }

                return result
            }
        }
}
