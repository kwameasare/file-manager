package ru.chipenable.filemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import rx.Observable

class MainActivity : AppCompatActivity() {

    private var f: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    override fun onBackPressed() {
        if (f?.backPressed() == false) {
            super.onBackPressed()
        }
    }
}
