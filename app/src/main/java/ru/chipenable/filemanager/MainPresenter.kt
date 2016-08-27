package ru.chipenable.filemanager

import java.io.File
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Pashgan on 11.08.2016.
 */
class MainPresenter : IMainPresenter {

    private val TAG: String = javaClass.name
    private var view: WeakReference<IMainView>
    private var fileInteractor: IFileInteractor
    private var mainFolder: String
    private var postions: Stack<Int>

    constructor(view: IMainView, fileInteractor: FileInteractor, mainFolder: String) {
        this.view = WeakReference(view)
        this.fileInteractor = fileInteractor
        this.mainFolder = mainFolder
        this.fileInteractor.enableHiddenFiles(false)
        this.postions = Stack()
    }

    override fun openHomeFolder() {
        openFolder(mainFolder)
    }

    override fun openFolder(item: Int, visiblePos: Int) {
        postions.push(visiblePos)
        setData(fileInteractor.getFolderContent(item), 0)
    }

    override fun openFolder(path: String) {
        setData(fileInteractor.getFolderContent(path), 0)
    }

    override fun back(): Boolean {
        if (fileInteractor.isRoot()){
            return false
        }
        else{
            val p = if (postions.empty()) 0 else postions.pop()
            setData(fileInteractor.getParentFolderContent(), p)
            return true
        }
    }

    private fun setData(list: List<File>?, visiblePos: Int){
        view.get()?.setData(list, visiblePos)
        view.get()?.showPath(fileInteractor.getCurFolder())
        view.get()?.showEmptyView(fileInteractor.isEmptyFolder())
    }


}