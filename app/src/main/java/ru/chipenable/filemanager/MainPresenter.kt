package ru.chipenable.filemanager

import android.util.Log
import rx.android.schedulers.AndroidSchedulers
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
        view.get()?.showLoading(true)
        fileInteractor.getFolderContent(mainFolder)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            view.get()?.showLoading(false)
                            view.get()?.setData(result)
                        }
                )
    }

    override fun openFolder(item: Int) {
        view.get()?.showLoading(true)
        fileInteractor.getFolderContent(item)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            view.get()?.showLoading(false)
                            view.get()?.setData(result)
                        }
                )
    }

    override fun back(): Boolean {
        if (fileInteractor.isRoot()){
            Log.d(TAG, "is root")
            return false
        }
        else{
            Log.d(TAG, "is not root")
            fileInteractor.getParentFolderContent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result ->
                                view.get()?.showLoading(false)
                                view.get()?.setData(result)
                            }
                    )
            return true
        }
    }
}