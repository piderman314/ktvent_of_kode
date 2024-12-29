package advent.util

data class Location(val x: Int, val y: Int)

infix fun Location.move(direction: Direction) = this move direction.distance

infix fun Location.move(distance: Distance) = Location(x + distance.dx, y + distance.dy)
