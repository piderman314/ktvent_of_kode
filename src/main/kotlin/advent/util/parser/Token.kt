package advent.util.parser

data class Token<T : Enum<T>>(val tokenType: T, val value: String)
