package ru.chipenable.filemanager

import java.io.File

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IFileInteractor {
    fun getFolderContent(path: String?): List<File>?
    fun getFolderContent(item: Int): List<File>?
    fun isRoot(): Boolean
    fun getParentFolderContent(): List<File>?
    fun getCurFolder(): String?
    fun isEmptyFolder(): Boolean
    fun enableHiddenFiles(flag: Boolean)
}