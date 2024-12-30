package advent.util.parser

interface BNFDefinition<BNF_TYPE : Enum<BNF_TYPE>, TOKEN_TYPE : Enum<TOKEN_TYPE>, M, R> {
    val options: List<BNFOption<BNF_TYPE, TOKEN_TYPE, M, R>>
}

interface BNFOption<BNF_TYPE : Enum<BNF_TYPE>, TOKEN_TYPE : Enum<TOKEN_TYPE>, M, R>

data class BNFTreeOption<BNF_TYPE : Enum<BNF_TYPE>, TOKEN_TYPE : Enum<TOKEN_TYPE>, M, R>(
    val options: List<BNF_TYPE>,
    val astConstructor: (List<ASTNode<BNF_TYPE, M, R>>) -> ASTNode<BNF_TYPE, M, R>,
    val type: BNFType = BNFType.TREE
) : BNFOption<BNF_TYPE, TOKEN_TYPE, M, R>

data class BNFLeafOption<BNF_TYPE : Enum<BNF_TYPE>, TOKEN_TYPE : Enum<TOKEN_TYPE>, M, R>(
    val tokenType: TOKEN_TYPE,
    val astConstructor: (Token<TOKEN_TYPE>) -> ASTNode<BNF_TYPE, M, R>,
    val type: BNFType = BNFType.LEAF
) : BNFOption<BNF_TYPE, TOKEN_TYPE, M, R>

enum class BNFType {
    TREE,
    LEAF
}
