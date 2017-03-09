package com.github.rhencke.intellij_pony

import com.github.rhencke.intellij_pony.parser.PonyParser
import com.github.rhencke.intellij_pony.psi.PonyElementTypes
import com.github.rhencke.intellij_pony.psi.PonyFile
import com.github.rhencke.intellij_pony.psi.PonyTokenTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class PonyParserDefinition : ParserDefinition {
    override fun createLexer(project: Project) = PonyLexerAdapter()
    override fun getWhitespaceTokens() = WHITE_SPACES
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = STRING_LITERALS
    override fun createParser(project: Project) = PonyParser()
    override fun getFileNodeType() = FILE
    override fun createFile(viewProvider: FileViewProvider) = PonyFile(viewProvider)
    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode) = ParserDefinition.SpaceRequirements.MAY
    override fun createElement(node: ASTNode): PsiElement = PonyElementTypes.Factory.createElement(node)

    companion object {
        private val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        private val COMMENTS = TokenSet.create(PonyTokenTypes.BLOCK_COMMENT.element, PonyTokenTypes.LINE_COMMENT.element)
        private val STRING_LITERALS = TokenSet.create(PonyTokenTypes.STRING.element)
        private val FILE = IFileElementType(PonyLanguage)
    }
}

