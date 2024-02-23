package com.example.miwokapp

import android.media.AudioManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class PhraseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val phrase = inflater.inflate(R.layout.activity_numbers,container,false)

        // we pass an ArrayList with the data type of a data class of variable @param phrases
        val phrases = arrayListOf<Word>()
        // add the data for the Array List called from the @param Word data class
        phrases.add(
            Word(
                getString(R.string.where_are_you_going),
                getString(R.string.minto_wuksus),
                0,
                R.raw.phrase_where_are_you_going
            )
        )
        phrases.add(
            Word(
                getString(R.string.what_is_your_name),
                getString(R.string.tinn_oyaase_n),
                0,
                R.raw.phrase_what_is_your_name
            )
        )
        phrases.add(
            Word(
                getString(R.string.my_name_is),
                getString(R.string.oyaaset),
                0,
                R.raw.phrase_my_name_is
            )
        )
        phrases.add(
            Word(
                getString(R.string.how_are_you_feeling),
                getString(R.string.mich_ks_s),
                0,
                R.raw.phrase_how_are_you_feeling
            )
        )
        phrases.add(
            Word(
                getString(R.string.i_m_feeling_good),
                getString(R.string.kuchi_achit),
                0,
                R.raw.phrase_im_feeling_good
            )
        )
        phrases.add(
            Word(
                getString(R.string.are_you_coming),
                getString(R.string.n_s_aa),
                0,
                R.raw.phrase_are_you_coming
            )
        )
        phrases.add(
            Word(
                getString(R.string.yes_i_m_coming),
                getString(R.string.h_n_m),
                0,
                R.raw.phrase_yes_im_coming
            )
        )
        phrases.add(
            Word(
                getString(R.string.i_m_coming),
                getString(R.string.n_m),
                0,
                R.raw.phrase_im_coming
            )
        )
        phrases.add(
            Word(
                getString(R.string.let_s_go),
                getString(R.string.yoowutis),
                0,
                R.raw.phrase_lets_go
            )
        )
        phrases.add(
            Word(
                getString(R.string.come_here),
                getString(R.string.nni_nem),
                0,
                R.raw.phrase_come_here
            )
        )

        // call the WordAdaptor class of type context, resource and MutableList
        val adaptor = WordAdaptor(requireActivity(),R.color.category_phrases, phrases)
        adaptor.audio = requireActivity().getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager
        // call the Listview the ArrayAdaptor will recycle
        val phrasesView = phrase.findViewById<ListView>(R.id.list)
        // attach the ArrayAdaptor to the Listview
        phrasesView.adapter = adaptor

        return phrase
    }
}