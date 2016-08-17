package ru.chipenable.filemanager

import rx.Observable
import java.io.File

/**
 * Created by Pashgan on 11.08.2016.
 */
interface IFileInteractor {
    fun getFolderContent(path: String?): Observable<List<File>?>
    fun getFolderContent(item: Int): Observable<List<File>?>
    fun isRoot(): Boolean
    fun getParentFolderContent(): Observable<List<File>?>
}