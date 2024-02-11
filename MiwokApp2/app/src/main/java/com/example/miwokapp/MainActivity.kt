package com.example.miwokapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miwokapp.ui.theme.MiwokAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numbers = findViewById<TextView>(R.id.numbers)
        numbers.setOnClickListener(View.OnClickListener {
            val numbers = Intent(this, NumbersActivity::class.java)
            startActivity(numbers)
        })
        val family = findViewById<TextView>(R.id.family)
        family.setOnClickListener(View.OnClickListener {
            val family = Intent(this, FamilyActivity::class.java)
            startActivity(family)
        })
        val colors = findViewById<TextView>(R.id.colors)
        colors.setOnClickListener(View.OnClickListener {
            val colors = Intent(this, ColorsActivity::class.java)
            startActivity(colors)
        })
        val phrases = findViewById<TextView>(R.id.phrases)
        phrases.setOnClickListener(View.OnClickListener {
            val phrases = Intent(this, PhrasesActivity::class.java)
            startActivity(phrases)
        })


    }
}