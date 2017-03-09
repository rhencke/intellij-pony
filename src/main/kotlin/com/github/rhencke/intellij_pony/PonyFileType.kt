package com.github.rhencke.intellij_pony

import com.intellij.openapi.fileTypes.LanguageFileType

object PonyFileType : LanguageFileType(PonyLanguage) {
    override fun getIcon() = PonyIcons.file
    override fun getName() = "Pony File"
    override fun getDefaultExtension() = "pony"
    override fun getDescription() = "A file with Pony source code"
}

