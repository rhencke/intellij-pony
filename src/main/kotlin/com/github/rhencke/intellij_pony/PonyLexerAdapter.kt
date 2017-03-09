package com.github.rhencke.intellij_pony

import com.intellij.lexer.FlexAdapter

class PonyLexerAdapter : FlexAdapter(PonyLexer(null))


