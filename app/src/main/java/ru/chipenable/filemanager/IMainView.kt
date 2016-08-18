package ru.chipenable.filemanager

import java.io.File

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IMainView {
    fun setData(list: List<File>?)
    fun openFile(path: String)
}