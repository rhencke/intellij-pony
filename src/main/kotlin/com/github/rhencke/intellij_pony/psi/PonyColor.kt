package com.github.rhencke.intellij_pony.psi

import com.github.rhencke.intellij_pony.PonyLanguage
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Colors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor

enum class PonyColor(displayName: String, fallbackType: TextAttributesKey) {
    // TODO Make match Pony terminology
    // TODO? Further refine categories for more flexibility in colorization
    KEYWORD("Keyword", Colors.KEYWORD),
    OPERATION_SIGN("Operation", Colors.OPERATION_SIGN),
    BLOCK_COMMENT("Block comment", Colors.BLOCK_COMMENT),
    DOT("Dot", Colors.DOT),
    PREDEFINED_SYMBOL("Predefined symbol", Colors.PREDEFINED_SYMBOL),
    NUMBER("Number", Colors.NUMBER),
    IDENTIFIER("Identifier", Colors.IDENTIFIER),
    BRACKETS("Brackets", Colors.BRACKETS),
    BRACES("Braces", Colors.BRACES),
    LINE_COMMENT("Line comment", Colors.LINE_COMMENT),
    PARENTHESES("Parentheses", Colors.PARENTHESES),
    SEMICOLON("Semicolon", Colors.SEMICOLON),
    STRING("String", Colors.STRING);

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("${PonyLanguage.upperId}_$name", fallbackType)
    val descriptor = AttributesDescriptor(displayName, textAttributesKey)
}