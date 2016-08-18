package ru.chipenable.filemanager

import java.lang.ref.WeakReference

/**
 * Created by Pashgan on 11.08.2016.
 */
class MainPresenter : IMainPresenter {

    private val TAG: String = javaClass.name
    private var view: WeakReference<IMainView>
    private var fileInteractor: IFileInteractor
    private var mainFolder: String?

    constructor(view: IMainView, fileInteractor: FileInteractor, mainFolder: String?) {
        this.view = WeakReference(view)
        this.fileInteractor = fileInteractor
        this.mainFolder = mainFolder
    }

    override fun openHomeFolder() {
        view.get()?.setData(fileInteractor.getFolderContent(mainFolder))
    }

    override fun openFolder(item: Int) {
        view.get()?.setData(fileInteractor.getFolderContent(item))
    }

    override fun back(): Boolean {
        if (fileInteractor.isRoot()){
            return false
        }
        else{
            view.get()?.setData(fileInteractor.getParentFolderContent())
            return true
        }
    }
}