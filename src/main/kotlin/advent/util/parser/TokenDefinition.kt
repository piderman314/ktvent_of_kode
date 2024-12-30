package advent.util.parser

interface TokenDefinition<T : Enum<T>> {
    val tokenType: T
    val definitionType: TokenDefinitionType
    val string: String?
    val regex: Regex?
}

enum class TokenDefinitionType {
    STRING,
    REGEX
}
