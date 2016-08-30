package ru.chipenable.filemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*


class MainActivity : AppCompatActivity(), SelectableTextView.OnClick {

    private var f: MainFragment? = null
    private lateinit var pathView: SelectableTextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerList: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pathView = this.current_path
        pathView.setOnWordClickListener(this)
        drawerLayout = this.drawer_layout
        drawerList = this.drawer_list

        drawerList.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.disks))

        val fm: FragmentManager = supportFragmentManager
        if (fm.findFragmentById(R.id.fragment_container) == null) {
            f = MainFragment()
            fm.beginTransaction()
                    .add(R.id.fragment_container, f)
                    .commit()
        }
        else{
            f = fm.findFragmentById(R.id.fragment_container) as MainFragment
        }
    }

    override fun onClick(path: String) {
        f?.onPathClick(path)
    }

    override fun onBackPressed() {
        if (f?.backPressed() == false) {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home && f?.backPressed() == true) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showPath(path: String?){
        pathView.text = path
    }
}
