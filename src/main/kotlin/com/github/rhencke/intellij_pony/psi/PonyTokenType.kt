package com.github.rhencke.intellij_pony.psi

import com.github.rhencke.intellij_pony.PonyLanguage
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.tree.IElementType

class PonyTokenType(debugName: String, fallbackKey: TextAttributesKey?) : IElementType(debugName, PonyLanguage) {
    override fun toString() = "PonyTokenType.${super.toString()}"

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("${PonyLanguage.upperId}_$debugName", fallbackKey)
    val textAttributesKeys = arrayOf(textAttributesKey)
}