package ru.chipenable.filemanager

import java.io.File

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IMainView {
    fun setData(list: List<File>?, visiblePos: Int)
    fun openFile(path: String)
    fun showPath(path: String?)
    fun showEmptyView(enable: Boolean)
}