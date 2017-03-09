package com.github.rhencke.intellij_pony

import com.intellij.openapi.fileTypes.FileTypeConsumer
import com.intellij.openapi.fileTypes.FileTypeFactory

class PonyFileTypeFactory : FileTypeFactory() {
    override fun createFileTypes(ftc: FileTypeConsumer) {
        ftc.consume(PonyFileType, PonyFileType.defaultExtension)
    }
}