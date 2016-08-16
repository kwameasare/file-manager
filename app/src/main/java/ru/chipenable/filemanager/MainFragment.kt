package ru.chipenable.filemanager


import android.content.ContextWrapper
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import java.io.File


/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment(), IMainView {

    lateinit var presenter: IMainPresenter
    var progressBar: ProgressBar? = null
    lateinit var listView: ListView
    lateinit var adapter: FileAdapter

    /** fragment lifecycle methods */

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)
        progressBar = view.findViewById(R.id.progress_bar) as ProgressBar
        listView = view.findViewById(R.id.file_list) as ListView
        adapter = FileAdapter(activity, R.layout.file_item)
        listView.adapter = adapter
        presenter = MainPresenter(this, FileInteractor(), Environment.getExternalStorageDirectory().absolutePath)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadFileList()
    }

    /** view interface methods */

    override fun showLoading(enable: Boolean) {
        progressBar?.visibility = if (enable) View.VISIBLE else View.GONE
    }

    override fun setData(list: List<File>) {
        adapter.setAdapterList(list)
    }
}
