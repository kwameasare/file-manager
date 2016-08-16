package ru.chipenable.filemanager

import android.util.Log
import rx.Observable
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.io.File
import java.util.*

/**
 * Created by Pashgan on 11.08.2016.
 */
class FileInteractor: IFileInteractor {

    val TAG: String = javaClass.name
    lateinit var curFolderContent: List<File>

    override fun getFolderContent(path: String?): Observable<List<File>> {
        var file: File = File(path)
        curFolderContent = file.listFiles().asList()
        return Observable.just(curFolderContent)
                         .subscribeOn(Schedulers.io())
    }
}