package com.example.nigeriaguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class lagos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lagos)
        supportFragmentManager.beginTransaction().replace(R.id.lg1, LagosFragment()).commit()
    }
}