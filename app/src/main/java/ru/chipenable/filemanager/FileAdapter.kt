package ru.chipenable.filemanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.File
import java.util.zip.Inflater

/**
 * Created by Pashgan on 16.08.2016.
 */
class FileAdapter: BaseAdapter {

    private lateinit var list: List<File>
    private var inflater: LayoutInflater
    private var layoutRes: Int

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

        var file: File = list[index]
        var name: TextView = view.findViewById(R.id.file_name) as TextView
        name.text = file.name
        var image: ImageView = view.findViewById(R.id.file_icon) as ImageView
        var imageRes: Int
        if (file.isDirectory){
            if (file.canRead()){
                imageRes = R.drawable.ic_folder
            }
            else {
                imageRes = R.drawable.ic_disable_folder
            }
        }
        else {
            imageRes = R.drawable.ic_file
        }

        image.setImageResource(imageRes)

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

    fun setAdapterList(list: List<File>){
        this.list = list
        notifyDataSetChanged()
    }


}