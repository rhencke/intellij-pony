package com.github.rhencke.intellij_pony.psi.impl

import com.github.rhencke.intellij_pony.PonyElementFactory
import com.github.rhencke.intellij_pony.psi.PonyClassDef
import com.intellij.psi.PsiElement


object PonyPsiImplUtil {
    @JvmStatic fun getName(element: PonyClassDef): String {
        return element.idDecl.text
    }

    @JvmStatic fun setName(element: PonyClassDef, newName: String): PsiElement {
        val idNode = element.idDecl
        val property = PonyElementFactory.createId(element.project, newName)
        element.node.replaceChild(idNode.node, property.node)
        return element
    }

    @JvmStatic fun getNameIdentifier( element:PonyClassDef):PsiElement {
        return element.idDecl
    }

    @JvmStatic fun toString(element:PonyClassDef): String {
        return element.idDecl.text
    }
}