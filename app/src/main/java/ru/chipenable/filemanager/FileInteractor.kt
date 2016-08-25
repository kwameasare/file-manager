package ru.chipenable.filemanager

import android.util.Log
import rx.Observable
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.io.File
import java.util.*

/**
 * Created by Pashgan on 11.08.2016.
 */
class FileInteractor: IFileInteractor {

    private val TAG: String = javaClass.name
    private lateinit var curFile: File
    private lateinit var curFolderContent: List<File>
    private var flagHidden: Boolean = false

    override fun getFolderContent(path: String?): List<File>? {
        curFile = File(path)
        return getCurFolderContent(flagHidden)
    }

    override fun getFolderContent(item: Int): List<File>? {
        var result: List<File>? = null
        val file: File = curFolderContent[item]
        if (file.isDirectory && file.canRead()) {
            curFile = file
            result = getCurFolderContent(flagHidden)
        }
        return result
    }

    private fun getCurFolderContent(flagHidden: Boolean): List<File>{
        curFolderContent = curFile.listFiles().asList()
                .sortedWith(FileComparators.alphabetComparator)
                .filter { f:File -> if (flagHidden) {true} else {!f.isHidden} }
        return curFolderContent
    }

    override fun isRoot(): Boolean {
        return curFile.parentFile == null
    }

    override fun getParentFolderContent(): List<File>? {
        return getFolderContent(curFile.parentFile.absolutePath)
    }

    override fun getCurFolder(): String {
        return curFile.absolutePath
    }

    override fun isEmptyFolder(): Boolean {
        return curFile.isDirectory && curFolderContent.size == 0
    }

    override fun enableHiddenFiles(flag: Boolean) {
        flagHidden = flag
    }
}