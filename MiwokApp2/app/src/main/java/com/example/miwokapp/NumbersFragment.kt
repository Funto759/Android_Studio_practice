package com.example.miwokapp

import android.media.AudioManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NumbersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
       val textView = inflater.inflate(R.layout.activity_numbers,container,false)

        // we pass an ArrayList with the data type of a data class of variable @param numbers
        val words = ArrayList<Word>()
        // add the data for the Array List called from the @param Word data class
        words.add(Word(getString(R.string.one), getString(R.string.lutti),R.drawable.number_one,R.raw.number_one))
        words.add(Word(getString(R.string.two), getString(R.string.otiiko),R.drawable.number_two,R.raw.number_two))
        words.add(Word(getString(R.string.three),
            getString(R.string.tolookosu),R.drawable.number_three,R.raw.number_three))
        words.add(Word(getString(R.string.four), getString(R.string.oyyisa),R.drawable.number_four,R.raw.number_four))
        words.add(Word(getString(R.string.five), getString(R.string.massokka),R.drawable.number_five,R.raw.number_five))
        words.add(Word(getString(R.string.six), getString(R.string.temmokka),R.drawable.number_six,R.raw.number_six))
        words.add(Word(getString(R.string.seven),
            getString(R.string.kenekaku),R.drawable.number_seven,R.raw.number_seven))
        words.add(Word(getString(R.string.eight),
            getString(R.string.kawinta),R.drawable.number_eight,R.raw.number_eight))
        words.add(Word(getString(R.string.nine), getString(R.string.wo_e),R.drawable.number_nine,R.raw.number_nine))
        words.add(Word(getString(R.string.ten), getString(R.string.na_aacha),R.drawable.number_ten,R.raw.number_ten))

// call the WordAdaptor class of type context, resource and MutableList
        val adaptor = WordAdaptor(requireActivity(),R.color.category_numbers, words)
        // call the Listview the ArrayAdaptor will recycle
        adaptor.audio = requireActivity().getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager
        val numberView = textView.findViewById<ListView>(R.id.list)
        // attach the ArrayAdaptor to the Listview
        numberView.adapter = adaptor

        return textView
    }
}