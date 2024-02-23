package com.example.miwokapp

import android.media.AudioManager
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sets the Activity Layout to the layout adopted for the ArrayAdaptor
        setContentView(R.layout.activity_container)
        supportFragmentManager.beginTransaction().replace(R.id.container, NumbersFragment()).commit()




    }
}