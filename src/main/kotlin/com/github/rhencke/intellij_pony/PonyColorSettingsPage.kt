package com.github.rhencke.intellij_pony

import com.github.rhencke.intellij_pony.psi.PonyColor
import com.github.rhencke.intellij_pony.psi.PonyTokenTypes
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

object PonyColorSettingsPage : ColorSettingsPage {
    private val attributeDescs = PonyColor.values().map { it.descriptor }.toTypedArray()

    override fun getHighlighter(): SyntaxHighlighter = PonySyntaxHighlighter()
    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null
    override fun getIcon(): Icon = PonyIcons.file
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = attributeDescs
    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY
    override fun getDisplayName(): String = "Pony"

    override fun getDemoText(): String = """
// TODO: some nice demo text
"""

}