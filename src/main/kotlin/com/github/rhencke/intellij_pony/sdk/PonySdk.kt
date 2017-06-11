package com.github.rhencke.intellij_pony.sdk

import com.github.rhencke.intellij_pony.PonyIcons
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.projectRoots.*
import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import org.jdom.Element
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.TimeUnit
import javax.swing.Icon

object PonySdkType : SdkType("Pony SDK") {
    private val log = Logger.getInstance(PonySdkType.javaClass)
    private val ponyc = if (SystemInfo.isWindows) "ponyc.exe" else "ponyc"

    override fun saveAdditionalData(additionalData: SdkAdditionalData, additional: Element) {
        // TODO?
    }


    override fun createAdditionalDataConfigurable(sdkModel: SdkModel, sdkModificator: SdkModificator): AdditionalDataConfigurable? {
        // TODO?
        return null
    }

    override fun suggestHomePath(): String? = findPonyInPath()?.parent?.parent?.toRealPath()?.toString()

    override fun getVersionString(sdkHome: String): String? {
        val ponyc = getPonycFromSdkDir(sdkHome)

        if (ponyc == null) {
            log.warn("No valid ponyc found in '$sdkHome' during version check.")
            return null
        }

        val ponycVersionExec = Runtime.getRuntime().exec(arrayOf(ponyc.toString(), "--version"))
        val out = ByteArrayOutputStream()
        val err = ByteArrayOutputStream()
        Thread { ponycVersionExec.errorStream.copyTo(err) }.start()
        Thread { ponycVersionExec.inputStream.copyTo(out) }.start()

        if (!ponycVersionExec.waitFor(100, TimeUnit.MILLISECONDS)) {
            ponycVersionExec.destroyForcibly()
            log.warn("Timed out waiting for '$ponyc' to print its version.")
            return null
        }

        val ponycExitValue = ponycVersionExec.exitValue()

        if (ponycExitValue != 0) {
            log.warn("'$ponyc' returned '$ponycExitValue' (not 0) when checking its version.")
            return null
        }

        // At the time of this writing, ponyc --version is of the form:
        // 0.9.0-5a0bd57 [release]
        // compiled with: llvm 3.9.0 -- ?
        val ponyVersion = out.toString().lines().firstOrNull()

        if (ponyVersion == null) {
            log.warn("'$ponyc' printed no output when checking its version.)")
            return null
        }

        return ponyVersion
    }

    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String = "Pony"

    override fun isValidSdkHome(path: String): Boolean {
        val isValid = getPonycFromSdkDir(path) != null
        return isValid
    }

    private fun getPonycFromSdkDir(path: String): Path? {
        val ponyc = Paths.get(path).resolve("ponyc").resolve("bin").resolve(ponyc)
        return if (isValidPonyc(ponyc)) ponyc else null
    }

    // TODO?  More thorough check?
    private fun isValidPonyc(ponyc: Path): Boolean = ponyc.exists()

    override fun getPresentableName(): String = "Pony SDK"
    override fun getIcon(): Icon = PonyIcons.logo

    private fun findPonyInPath(): Path? {
        val paths = System.getenv("PATH")?.split(File.pathSeparatorChar) ?: emptyList()

        return paths
                .map { Paths.get(it, ponyc) }
                .firstOrNull { isValidPonyc(it) }
    }
}
