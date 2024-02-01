package com.example.greetingcard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    var numberOfCoffe = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    // performs the funtion of increment
    fun plus(view: View?) {
        numberOfCoffe = numberOfCoffe + 1
        display(numberOfCoffe)
    }
    // function of decrement
    fun minus(view: View?) {
        numberOfCoffe = numberOfCoffe - 1
        display(numberOfCoffe)
    }
// displays the price of the cofee
    fun message(view: View?) {
        displayMessage(priceSummary())
    }
    fun calculatePrice(): Int {
        val price = numberOfCoffe * 5
        return price
    }
    fun priceSummary(): String {
        val mes = "Name: ${name().toString()}\n" +
                "${check()}\n" +
                "Quantity:$numberOfCoffe\n" +
                "Total: $${price()}\n" +
                "Thank you!"
        return mes
    }


    /**

    This method displays the given quantity value on the screen.
     */
    private fun display(number: Int) {
        val quantityTextView =
            findViewById(com.example.greetingcard.R.id.quantity_text_view) as TextView
        quantityTextView.text = "" + number
    }

    /**
     * This method displays the given price on the screen.
     */

    private fun displayMessage(message: String) {
        val priceTextView = findViewById(com.example.greetingcard.R.id.textView2) as TextView
        priceTextView.setText(message)
    }

    private fun check():String{
        val whipped =
            findViewById<CheckBox>(com.example.greetingcard.R.id.notify_me_checkbox) as CheckBox
        val hey = whipped.isChecked

        val chocolate =
            findViewById<CheckBox>(com.example.greetingcard.R.id.notify_me_checkbox2) as CheckBox
        val choc = chocolate.isChecked
        val h = if (hey == true && choc == true) {
            "Whipped cream\n" + "Chocolate"
        } else if (hey == true && choc == false) {
            "Whipped cream"
        } else if (hey == false && choc == true) {
            "Chocolate"
        } else {
            "No toppings"
        }
        return h
    }

    private fun price():Int{
        val whipped =
            findViewById<CheckBox>(com.example.greetingcard.R.id.notify_me_checkbox) as CheckBox
        val hey = whipped.isChecked

        val chocolate =
            findViewById<CheckBox>(com.example.greetingcard.R.id.notify_me_checkbox2) as CheckBox
        val choc = chocolate.isChecked
        val p = if (hey == true && choc == true) {
            calculatePrice()+2
        } else if (hey == true && choc == false) {
            calculatePrice()+1
        } else if (hey == false && choc == true) {
            calculatePrice() + 1
        } else {
            calculatePrice() + 0
        }
        return p
    }


    private fun name():String{
        val name = findViewById<EditText>(R.id.name_description_view) as EditText
        val n = name.text.toString()
        return n
    }

}



