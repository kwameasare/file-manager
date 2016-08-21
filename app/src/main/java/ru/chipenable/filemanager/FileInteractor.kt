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

    override fun getFolderContent(path: String?): List<File>? {
        curFile = File(path)
        curFolderContent = curFile.listFiles().asList().sortedWith(FileComparators.alphabetComparator)
        return curFolderContent
    }

    override fun getFolderContent(item: Int): List<File>? {
        var result: List<File>? = null
        val file: File = curFolderContent[item]
        if (file.isDirectory && file.canRead()) {
            curFile = file
            curFolderContent = file.listFiles().asList().sortedWith(FileComparators.alphabetComparator)
            result = curFolderContent
        }
        return result
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
        return curFile.isDirectory && curFile.list().size == 0
    }
}