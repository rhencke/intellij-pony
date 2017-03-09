package com.github.rhencke.intellij_pony.psi.impl

import com.github.rhencke.intellij_pony.psi.PonyElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

open class PonyElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), PonyElement

