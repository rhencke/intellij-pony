package com.github.rhencke.intellij_pony.psi

import com.github.rhencke.intellij_pony.PonyLanguage
import com.intellij.psi.tree.IElementType

class PonyTokenType(debugName: String, color: PonyColor) : IElementType(debugName, PonyLanguage) {
    override fun toString() = "PonyTokenType.${super.toString()}"

    val colorArr = arrayOf(color.textAttributesKey)
}