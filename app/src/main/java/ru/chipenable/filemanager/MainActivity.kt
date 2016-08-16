package ru.chipenable.filemanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fm: FragmentManager = supportFragmentManager
        var f: Fragment? = fm.findFragmentById(R.id.fragment_container)
        if (f == null) {
            f = MainFragment()
            fm.beginTransaction()
                    .add(R.id.fragment_container, f)
                    .commit()
        }
    }
}
