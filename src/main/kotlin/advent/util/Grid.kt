package advent.util

class Grid<T>(val xDimension: Int, val yDimension: Int, initialElement: T) : Iterable<T> {

    val grid: MutableList<T> = MutableList(xDimension * yDimension) { _ -> initialElement }

    // Modifications
    fun replace(iterable: Iterable<Location>, transformation: (T) -> T) {
        iterable.forEach { this[it] = transformation.invoke(this[it]) }
    }

    // Operators
    operator fun get(x: Int, y: Int): T = grid[toIndex(x, y)]

    operator fun get(location: Location): T = grid[toIndex(location)]

    operator fun get(iterable: Iterable<Location>): List<T> = iterable.map { this[it] }

    operator fun set(x: Int, y: Int, value: T) = grid.set(toIndex(x, y), value)

    operator fun set(location: Location, value: T) = grid.set(toIndex(location), value)

    operator fun set(iterable: Iterable<Location>, value: T) = iterable.forEach { this[it] = value }

    // Utils
    private fun toIndex(x: Int, y: Int) = x + y * xDimension

    private fun toIndex(location: Location) = toIndex(location.x, location.y)

    // Iterable
    override fun iterator(): Iterator<T> = get(Location(0, 0)..<Location(xDimension, yDimension)).iterator()
}
