package ru.chipenable.filemanager

import java.io.File
import java.lang.ref.WeakReference

/**
 * Created by Pashgan on 11.08.2016.
 */
class MainPresenter : IMainPresenter {

    private val TAG: String = javaClass.name
    private var view: WeakReference<IMainView>
    private var fileInteractor: IFileInteractor
    private var mainFolder: String

    constructor(view: IMainView, fileInteractor: FileInteractor, mainFolder: String) {
        this.view = WeakReference(view)
        this.fileInteractor = fileInteractor
        this.mainFolder = mainFolder
        this.fileInteractor.enableHiddenFiles(false)
    }

    override fun openHomeFolder() {
        openFolder(mainFolder)
    }

    override fun openFolder(item: Int) {
        setData(fileInteractor.getFolderContent(item))
    }

    override fun openFolder(path: String) {
        setData(fileInteractor.getFolderContent(path))
    }

    override fun back(): Boolean {
        if (fileInteractor.isRoot()){
            return false
        }
        else{
            setData(fileInteractor.getParentFolderContent())
            return true
        }
    }

    private fun setData(list: List<File>?){
        view.get()?.setData(list)
        view.get()?.showPath(fileInteractor.getCurFolder())
        view.get()?.showEmptyView(fileInteractor.isEmptyFolder())
    }


}