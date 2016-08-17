package ru.chipenable.filemanager

import java.io.File

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IMainView {
    fun showLoading(enable: Boolean)
    fun setData(list: List<File>?)
}