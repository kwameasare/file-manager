package ru.chipenable.filemanager

import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action0
import rx.functions.Action1
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

/**
 * Created by Pashgan on 11.08.2016.
 */
class MainPresenter : IMainPresenter {

    var view: WeakReference<IMainView>
    var fileInteractor: IFileInteractor
    var mainFolder: String?

    constructor(view: IMainView, fileInteractor: FileInteractor, mainFolder: String?) {
        this.view = WeakReference(view)
        this.fileInteractor = fileInteractor
        this.mainFolder = mainFolder
    }

    override fun loadFileList() {
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
}