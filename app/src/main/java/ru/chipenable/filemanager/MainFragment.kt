package ru.chipenable.filemanager


import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), IMainView, FileRecyclerAdapter.OnItemClickListener{

    private val TAG: String = javaClass.name
    private lateinit var presenter: IMainPresenter
    private var progressBar: ProgressBar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: FileRecyclerAdapter
    private lateinit var emptyView: TextView
    private var curPos: Int = 0

    /** fragment lifecycle methods */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        progressBar = view.findViewById(R.id.progress_bar) as ProgressBar
        recyclerView = view.findViewById(R.id.file_list) as RecyclerView
        emptyView = view.findViewById(R.id.empty_view) as TextView

        layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        adapter = FileRecyclerAdapter(context)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        presenter = MainPresenter(this, FileInteractor(), Environment.getExternalStorageDirectory().absolutePath)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.openHomeFolder()
    }

    /** callback methods */

    override fun onItemClick(view: View, pos: Int) {
        var visiblePos = layoutManager.findFirstVisibleItemPosition()
        presenter.openFolder(pos, visiblePos)
    }

    fun onPathClick(path: String) {
        presenter.openFolder(path)
    }

    /** view interface methods */

    override fun setData(list: List<File>?, visiblePos: Int) {
        if (list != null) {
            adapter.setAdapterList(list)
            layoutManager.scrollToPositionWithOffset(visiblePos, 0)
        }
    }

    override fun openFile(path: String) {

    }

    override fun showPath(path: String?) {
        (activity as MainActivity).showPath(path)
    }

    override fun showEmptyView(enable: Boolean) {
        emptyView.visibility = if (enable) View.VISIBLE else View.GONE
    }

    /***/

    fun backPressed(): Boolean{
        return presenter.back()
    }
}
