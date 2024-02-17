package com.example.miwokapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class ColorsActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // sets the Activity Layout to the layout adopted for the ArrayAdaptor
        setContentView(R.layout.activity_numbers)

        // we pass an ArrayList with the data type of a data class of variable @param colors



        val colors = ArrayList<Word>()
        // add the data for the Array List called from the @param Word data class
        colors.add(Word(getString(R.string.red), getString(R.string.we_e_i),R.drawable.color_red,R.raw.color_red))
        colors.add(Word(getString(R.string.green),
            getString(R.string.chokokki),R.drawable.color_green,R.raw.color_green))
        colors.add(Word(getString(R.string.brown),
            getString(R.string.akaakki),R.drawable.color_brown,R.raw.color_brown))
        colors.add(Word(getString(R.string.gray), getString(R.string.we_e_ii),R.drawable.color_gray,R.raw.color_gray))
        colors.add(Word(getString(R.string.black),
            getString(R.string.kululli),R.drawable.color_black,R.raw.color_black))
        colors.add(Word(getString(R.string.white),
            getString(R.string.kelelli),R.drawable.color_white,R.raw.color_white))
        colors.add(Word(getString(R.string.dusty_yellow),
            getString(R.string.opiis),R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow))
        colors.add(Word(getString(R.string.mustard_yellow),
            getString(R.string.chiwii),R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow))

        // call the WordAdaptor class of type context, resource and MutableList
        val adaptor = WordAdaptor(this,R.color.category_colors,colors)
        // call the Listview the ArrayAdaptor will recycle
        var colorView = findViewById<ListView>(R.id.list)
        // attach the ArrayAdaptor to the Listview
        colorView.adapter = adaptor


//

//



    }
}