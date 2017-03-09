package com.github.rhencke.intellij_pony.psi

import com.github.rhencke.intellij_pony.PonyFileType
import com.github.rhencke.intellij_pony.PonyIcons
import com.github.rhencke.intellij_pony.PonyLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class PonyFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, PonyLanguage) {
    override fun getFileType() = PonyFileType
    override fun toString() = "Pony File"
    override fun getIcon(flags: Int) = PonyIcons.file
}
