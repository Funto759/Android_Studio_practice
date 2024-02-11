package com.example.miwokapp

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class NumbersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        val words = ArrayList<Word>()
        words.add(Word("one", "lutti"))
        words.add(Word("two", "otiiko"))
        words.add(Word("three", "tolookosu"))
        words.add(Word("four", "oyyisa"))
        words.add(Word("five", "massokka"))
        words.add(Word("six", "temmokka"))
        words.add(Word("seven", "kenekaku"))
        words.add(Word("eight", "kawinta"))
        words.add(Word("nine", "wo’e"))
        words.add(Word("ten", "na’aacha"))


        val adaptor = WordAdaptor(this,0,words)
        var listView = findViewById<ListView>(R.id.list)
       listView.adapter = adaptor
    }
}