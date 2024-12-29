package advent.util

enum class Direction(val distance: Distance) {

    NORTH(Distance(0, -1)),
    NORTH_EAST(Distance(1, -1)),
    EAST(Distance(1, 0)),
    SOUTH_EAST(Distance(1, 1)),
    SOUTH(Distance(0, 1)),
    SOUTH_WEST(Distance(-1, 1)),
    WEST(Distance(-1, 0)),
    NORTH_WEST(Distance(-1, -1))
}

fun Char.toDirection(): Direction =
    when (this) {
        '^' -> Direction.NORTH
        '>' -> Direction.EAST
        'v' -> Direction.SOUTH
        '<' -> Direction.WEST
        else -> throw IllegalArgumentException()
    }
