package com.github.rhencke.intellij_pony

import com.github.rhencke.intellij_pony.psi.PonyElementTypes
import com.github.rhencke.intellij_pony.psi.PonyFile
import com.github.rhencke.intellij_pony.psi.PonyIdDecl
import com.github.rhencke.intellij_pony.psi.PonyTokenTypes
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.tree.IElementType

object PonyElementFactory {
    fun createId(project: Project, name: String): PonyIdDecl {
        val file = createFile(project, "class $name")
        return file.firstChild.node.findChildByType(PonyElementTypes.ID_DECL)!! as PonyIdDecl
    }

    fun createFile(project: Project, text: String): PonyFile {
        return PsiFileFactory.getInstance(project).createFileFromText("dummy.pony", PonyFileType, text) as PonyFile
    }

    @JvmStatic fun getTokenType(name: String): IElementType {
        return PonyTokenTypes.valueOf(name).element
    }
}
