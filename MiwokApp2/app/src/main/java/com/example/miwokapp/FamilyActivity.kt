package com.example.miwokapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView

class FamilyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)

        val family = arrayListOf<Word>()
        family.add(Word("father","әpә"))
        family.add(Word("mother", "әṭa",))
        family.add(Word("son", "angsi"))
        family.add(Word("daughter", "tune"))
        family.add(Word("older brother", "taachi"))
        family.add(Word("younger brother", "chalitti"))
        family.add(Word("older sister", "teṭe"))
        family.add(Word("younger sister", "kolliti"))
        family.add(Word("grandmother", "ama"))
        family.add(Word("grandfather", "paapa"))

        val adaptor = WordAdaptor(this,0,family)
        val familyView = findViewById<ListView>(R.id.list)
        familyView.adapter=adaptor
    }


}