package com.example.android.pornactriss

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.pornactriss.fragments.GirlListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if(isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, GirlListFragment.newInstance())
                .commit()
        }
    }
}