package com.github.rhencke.intellij_pony

import com.github.rhencke.intellij_pony.psi.PonyTokenType
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class PonySyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return PonyLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> = when (tokenType) {
        is PonyTokenType -> tokenType.colorArr
        else -> emptyArray()
    }
}