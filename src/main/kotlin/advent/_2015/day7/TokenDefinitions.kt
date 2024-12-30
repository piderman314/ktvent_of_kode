package advent._2015.day7

import advent.util.parser.TokenDefinition
import advent.util.parser.TokenDefinitionType

enum class TokenDefinitions(
    override val definitionType: TokenDefinitionType,
    override val string: String?,
    override val regex: Regex?
) : TokenDefinition<TokenDefinitions> {

    OPERATOR_AND("AND"),
    OPERATOR_OR("OR"),
    OPERATOR_LSHIFT("LSHIFT"),
    OPERATOR_RSHIFT("RSHIFT"),
    OPERATOR_NOT("NOT"),
    OPERATOR_ASSIGNMENT("->"),
    NUMBER("\\d+".toRegex()),
    IDENTIFIER("[a-z]+".toRegex());

    constructor(string: String) : this(TokenDefinitionType.STRING, string, null)

    constructor(regex: Regex) : this(TokenDefinitionType.REGEX, null, regex)

    override val tokenType: TokenDefinitions = this
}
