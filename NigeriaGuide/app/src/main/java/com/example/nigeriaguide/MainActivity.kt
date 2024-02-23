package com.example.nigeriaguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.view)
        val b = mainPageViewAdaptor(supportFragmentManager)
        viewPager.adapter = b

        val tabLayout = findViewById<TabLayout>(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)
    }
}