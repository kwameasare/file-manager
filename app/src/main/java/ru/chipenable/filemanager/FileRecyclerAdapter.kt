package ru.chipenable.filemanager

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.io.File

/**
 * Created by Pashgan on 18.08.2016.
 */

class FileRecyclerAdapter : RecyclerView.Adapter<FileRecyclerAdapter.ItemHolder> {

    private val TAG = javaClass.name
    private lateinit var list: List<File>
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    constructor(){
        this.list = listOf()
    }

    constructor(list: List<File>){
        this.list = list
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Log.d(TAG, list[position].name)
        val file = list[position]
        holder.pos = position
        holder.name.text = file.name

        val imageRes: Int
        if (file.isDirectory){
            imageRes = if (file.canRead()) R.drawable.ic_folder else R.drawable.ic_disable_folder
        }
        else {
            imageRes = R.drawable.ic_file
        }
        holder.icon.setImageResource(imageRes)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.file_item, parent, false)
        return ItemHolder(view)
    }

    fun setAdapterList(list: List<File>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ItemHolder: RecyclerView.ViewHolder, View.OnClickListener {
        var name: TextView
        var icon: ImageView
        var pos: Int

        constructor(itemView: View): super(itemView){
            name = itemView.findViewById(R.id.file_name) as TextView
            icon = itemView.findViewById(R.id.file_icon) as ImageView
            pos = 0
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onItemClickListener?.onItemClick(view, pos)
        }
    }



}