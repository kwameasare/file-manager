package ru.chipenable.filemanager

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IMainPresenter {
    fun openHomeFolder()
    fun openFolder(item: Int)
    fun back(): Boolean
}