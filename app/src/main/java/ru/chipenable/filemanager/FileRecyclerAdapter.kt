package ru.chipenable.filemanager

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Pashgan on 18.08.2016.
 */

class FileRecyclerAdapter : RecyclerView.Adapter<FileRecyclerAdapter.ItemHolder> {

    private val TAG = javaClass.name
    private lateinit var list: List<File>
    private var onItemClickListener: OnItemClickListener? = null
    private lateinit var context: Context
    private var dateFormat: SimpleDateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())

    interface OnItemClickListener{
        fun onItemClick(view: View, pos: Int)
    }

    constructor(context: Context){
        this.list = listOf()
        this.context = context
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Log.d(TAG, list[position].name)
        val file = list[position]
        holder.pos = position
        holder.name.text = file.name
        holder.date.text = dateFormat.format(file.lastModified())

        val imageRes: Int
        if (file.isDirectory){
            imageRes = if (file.canRead()) R.drawable.ic_folder else R.drawable.ic_disable_folder
            holder.icon.setImageResource(imageRes)
        }
        else {
            when(file.extension){
                "jpg", "jpeg", "png", "bmp" -> {
                    Glide.with(context)
                            .load(file.absolutePath)
                            .override(100, 100)
                            .fitCenter()
                            .crossFade()
                            .into(holder.icon)
                }
                else -> {
                    imageRes = R.drawable.ic_file
                    holder.icon.setImageResource(imageRes)
                }
            }
        }
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
        var date: TextView
        var pos: Int

        constructor(itemView: View): super(itemView){
            name = itemView.findViewById(R.id.file_name) as TextView
            icon = itemView.findViewById(R.id.file_icon) as ImageView
            date = itemView.findViewById(R.id.file_date) as TextView
            pos = 0
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            onItemClickListener?.onItemClick(view, pos)
        }
    }



}