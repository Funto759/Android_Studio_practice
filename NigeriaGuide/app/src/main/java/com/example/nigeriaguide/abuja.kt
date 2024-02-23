package com.example.nigeriaguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class abuja : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abuja)
        supportFragmentManager.beginTransaction().replace(R.id.ab1,AbujaFragment()).commit()
    }
}