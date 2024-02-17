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
        // sets the main app layout to activity_main
        setContentView(R.layout.activity_main)

        //set the object of the numbers textview to the variable Numbers

        val numbers = findViewById<TextView>(R.id.numbers)
        // sets an OnClick listener on the variable Numbers
        numbers.setOnClickListener(View.OnClickListener {
            // passes an intent to switch to the NumbersActivity when it is clicked
            val numbers = Intent(this, NumbersActivity::class.java)
            startActivity(numbers)
        })
        //set the object of the numbers textview to the variable Family
        val family = findViewById<TextView>(R.id.family)
        // sets an OnClick listener on the variable Family
        family.setOnClickListener(View.OnClickListener {
            // passes an intent to switch to the FamilyActivity when it is clicked
            val family = Intent(this, FamilyActivity::class.java)
            startActivity(family)
        })
        //set the object of the numbers textview to the variable Colors

        val colors = findViewById<TextView>(R.id.colors)
        // sets an OnClick listener on the variable Colors
        colors.setOnClickListener(View.OnClickListener {
            // passes an intent to switch to the ColorsActivity when it is clicked
            val colors = Intent(this, ColorsActivity::class.java)
            startActivity(colors)
        })
        //set the object of the numbers textview to the variable Phrases

        val phrases = findViewById<TextView>(R.id.phrases)
        // sets an OnClick listener on the variable Phrases
        phrases.setOnClickListener(View.OnClickListener {
            // passes an intent to switch to the PhrasesActivity when it is clicked
            val phrases = Intent(this, PhrasesActivity::class.java)
            startActivity(phrases)
        })


    }
}