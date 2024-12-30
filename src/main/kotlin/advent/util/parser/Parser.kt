package advent.util.parser

class Parser<BNF_TYPE : Enum<BNF_TYPE>, TOKEN_TYPE : Enum<TOKEN_TYPE>, M, R>(
    val bnfDefinitions: Map<BNF_TYPE, List<BNFOption<BNF_TYPE, TOKEN_TYPE, M, R>>>,
    val tokens: List<Token<TOKEN_TYPE>>
) {
    fun parse(bnfType: BNF_TYPE): ASTNode<BNF_TYPE, M, R>? {
        return parse(bnfType, 0)?.node
    }

    private fun parse(bnfType: BNF_TYPE, index: Int): State<BNF_TYPE, M, R>? {
        for (option in bnfDefinitions[bnfType]!!) {
            val state = parse(option, index)
            if (state != null) {
                return state
            }
        }

        return null
    }

    private fun parse(bnfOption: BNFOption<BNF_TYPE, TOKEN_TYPE, M, R>, index: Int): State<BNF_TYPE, M, R>? {
        if (bnfOption is BNFTreeOption<BNF_TYPE, TOKEN_TYPE, M, R>) {
            return parseTree(bnfOption, index)
        } else if (bnfOption is BNFLeafOption<BNF_TYPE, TOKEN_TYPE, M, R>) {
            return parseLeaf(bnfOption, index)
        }

        return null
    }

    private fun parseLeaf(bnfOption: BNFLeafOption<BNF_TYPE, TOKEN_TYPE, M, R>, index: Int): State<BNF_TYPE, M, R>? {
        if (index >= tokens.size || tokens[index].tokenType != bnfOption.tokenType) {
            return null
        }

        val node = bnfOption.astConstructor.invoke(tokens[index])
        return node at (index + 1)
    }

    private fun parseTree(bnfOption: BNFTreeOption<BNF_TYPE, TOKEN_TYPE, M, R>, index: Int): State<BNF_TYPE, M, R>? {
        var currentIndex = index
        val nodes = mutableListOf<ASTNode<BNF_TYPE, M, R>>()
        for (bnfType in bnfOption.options) {
            val state = parse(bnfType, currentIndex) ?: return null
            nodes.add(state.node)
            currentIndex = state.index
        }

        val node = bnfOption.astConstructor.invoke(nodes)
        return node at currentIndex
    }
}

data class State<T : Enum<T>, M, R>(val node: ASTNode<T, M, R>, val index: Int)

infix fun <T : Enum<T>, M, R> ASTNode<T, M, R>.at(index: Int) = State(this, index)

class ParserException(message: String) : Exception(message)
