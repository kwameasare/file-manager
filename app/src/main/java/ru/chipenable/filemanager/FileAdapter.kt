package ru.chipenable.filemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import java.io.File
import java.util.zip.Inflater

/**
 * Created by Pashgan on 16.08.2016.
 */
class FileAdapter: BaseAdapter {

    lateinit var list: List<File>
    var inflater: LayoutInflater
    var layoutRes: Int

    public constructor(context: Context, layoutRes: Int): super(){
        this.list = listOf()
        this.inflater = LayoutInflater.from(context)
        this.layoutRes = layoutRes
    }

    override fun getView(index: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var view: View
        if (convertView == null){
            view = inflater.inflate(layoutRes, viewGroup, false)
        }
        else{
            view = convertView
        }

        var file: File = list.get(index)
        var name: TextView = view.findViewById(R.id.file_name) as TextView
        name.setText(file.name)

        return view
    }

    override fun getItem(index: Int): File {
        return list[index]
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    public fun setAdapterList(list: List<File>){
        this.list = list
        notifyDataSetChanged()
    }


}