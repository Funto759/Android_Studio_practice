package com.example.miwokapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ColorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        val numbers = ArrayList<Word>()
        numbers.add(Word("red","weṭeṭṭi"))
        numbers.add(Word("green","chokokki"))
        numbers.add(Word("brown","ṭakaakki"))
        numbers.add(Word("gray","weṭeṭṭi"))
        numbers.add(Word("black","kululli"))
        numbers.add(Word("white", "kelelli"))
        numbers.add(Word("dusty yellow","ṭopiisә"))
        numbers.add(Word("mustard yellow","chiwiiṭә"))

        val adaptor = WordAdaptor(this,0,numbers)
        var numberView = findViewById<ListView>(R.id.list)
        numberView.adapter = adaptor
    }
}