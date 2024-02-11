package com.example.miwokapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class PhrasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        val phrases = arrayListOf<Word>()
        phrases.add(Word("Where are you going?","minto wuksus"))
        phrases.add(Word("What is your name?", "tinnә oyaase'nә"))
        phrases.add(Word("My name is...", "oyaaset..."))
        phrases.add(Word("How are you feeling?", "michәksәs?"))
        phrases.add(Word("I’m feeling good.", "kuchi achit"))
        phrases.add(Word("Are you coming?", "әәnәs'aa?"))
        phrases.add(Word("Yes, I’m coming.", "hәә’ әәnәm"))
        phrases.add(Word("I’m coming.", "әәnәm"))
        phrases.add(Word("Let’s go.", "yoowutis"))
        phrases.add(Word("Come here.", "әnni'nem"))

        val adaptor = WordAdaptor(this,0,phrases)
        val phrasesView = findViewById<ListView>(R.id.list)
        phrasesView.adapter = adaptor
    }
}