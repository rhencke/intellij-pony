package com.github.rhencke.intellij_pony

import com.intellij.lang.Commenter

class PonyCommenter : Commenter {
    override fun getCommentedBlockCommentPrefix(): String? = null
    override fun getCommentedBlockCommentSuffix(): String? = null
    override fun getBlockCommentPrefix(): String? = "/*"
    override fun getBlockCommentSuffix(): String? = "*/"
    override fun getLineCommentPrefix(): String? = "//"
}