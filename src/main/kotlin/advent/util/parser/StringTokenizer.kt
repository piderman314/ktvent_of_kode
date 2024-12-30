package advent.util.parser

class StringTokenizer(internal var input: String) {
    fun mayBe(value: String) = input == value || mayBe("${value}\\s+".toRegex())

    fun mayBe(regex: Regex) = enhanceRegex(regex).find(input) != null

    fun mayRead(value: String): String? {
        if (!mayBe(value)) {
            return null
        }

        input = input.substring(value.length)
        skipWhitespace()
        return value
    }

    fun mayRead(regex: Regex): String? {
        val matchResult = enhanceRegex(regex).find(input) ?: return null

        val result = matchResult.value

        input = input.substring(result.length)
        skipWhitespace()
        return result
    }

    fun isEmpty() = input.isEmpty()

    fun nextToken() = mayRead("[^\\s]+".toRegex())

    private fun skipWhitespace() {
        mayRead("\\s+".toRegex())
    }

    private fun enhanceRegex(regex: Regex) = "^${regex.pattern}".toRegex()
}
