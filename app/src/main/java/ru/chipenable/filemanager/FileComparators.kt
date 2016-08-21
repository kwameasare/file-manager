package ru.chipenable.filemanager

import java.io.File
import java.util.*

/**
 * Created by Pashgan on 21.08.2016.
 */
object FileComparators {

        val alphabetComparator: Comparator<File> = Comparator {
            f1, f2 ->
            if (f1.isDirectory && !f2.isDirectory) {
                -1
            } else if (!f1.isDirectory && f2.isDirectory) {
                 1
            } else {
                f1.compareTo(f2)
            }
        }



}