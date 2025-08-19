package advent._2015.day7

import advent.util.parser.BNFLeafOption
import advent.util.parser.BNFOption
import advent.util.parser.BNFTreeOption

enum class BNFDefinitions {
    ASSIGNMENT,
    EXPRESSION,
    ASSIGNMENT_OPERATOR,
    UNARY_OPERATOR,
    BINARY_OPERATOR,
    TERM,
    NUMBER,
    IDENTIFIER
}

val bnfDefinitions:
    Map<BNFDefinitions, List<BNFOption<BNFDefinitions, TokenDefinitions, MutableMap<String, Long>, Long>>> =
    mapOf(
        BNFDefinitions.ASSIGNMENT to
            listOf(
                BNFTreeOption(
                    listOf(BNFDefinitions.EXPRESSION, BNFDefinitions.ASSIGNMENT_OPERATOR, BNFDefinitions.IDENTIFIER),
                    ::Assignment)),
        BNFDefinitions.ASSIGNMENT_OPERATOR to
            listOf(
                BNFLeafOption(TokenDefinitions.OPERATOR_ASSIGNMENT, { Operator(BNFDefinitions.BINARY_OPERATOR, it) })),
        BNFDefinitions.EXPRESSION to
            listOf(
                BNFTreeOption(
                    listOf(BNFDefinitions.TERM, BNFDefinitions.BINARY_OPERATOR, BNFDefinitions.TERM),
                    ::BinaryExpression),
                BNFTreeOption(listOf(BNFDefinitions.UNARY_OPERATOR, BNFDefinitions.TERM), ::UnaryExpression),
                BNFTreeOption(listOf(BNFDefinitions.TERM), ::TermExpression)),
        BNFDefinitions.TERM to
            listOf(
                BNFTreeOption(listOf(BNFDefinitions.NUMBER), ::Term),
                BNFTreeOption(listOf(BNFDefinitions.IDENTIFIER), ::Term)),
        BNFDefinitions.BINARY_OPERATOR to
            listOf(
                BNFLeafOption(TokenDefinitions.OPERATOR_AND, { Operator(BNFDefinitions.BINARY_OPERATOR, it) }),
                BNFLeafOption(TokenDefinitions.OPERATOR_OR, { Operator(BNFDefinitions.BINARY_OPERATOR, it) }),
                BNFLeafOption(TokenDefinitions.OPERATOR_LSHIFT, { Operator(BNFDefinitions.BINARY_OPERATOR, it) }),
                BNFLeafOption(TokenDefinitions.OPERATOR_RSHIFT, { Operator(BNFDefinitions.BINARY_OPERATOR, it) })),
        BNFDefinitions.UNARY_OPERATOR to
            listOf(BNFLeafOption(TokenDefinitions.OPERATOR_NOT, { Operator(BNFDefinitions.UNARY_OPERATOR, it) })),
        BNFDefinitions.NUMBER to listOf(BNFLeafOption(TokenDefinitions.NUMBER, ::Number)),
        BNFDefinitions.IDENTIFIER to listOf(BNFLeafOption(TokenDefinitions.IDENTIFIER, ::Identifier)))
