package com.example.miwokapp

import android.media.AudioManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class FamilyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fam = inflater.inflate(R.layout.activity_numbers,container,false)

        // we pass an ArrayList with the data type of a data class of variable @param family
        val family = arrayListOf<Word>()
        // add the data for the Array List called from the @param Word data class
        family.add(Word(getString(R.string.father), getString(R.string.p),R.drawable.family_father,R.raw.family_father))
        family.add(Word(getString(R.string.mother), getString(R.string.a),R.drawable.family_mother,R.raw.family_mother))
        family.add(Word(getString(R.string.son), getString(R.string.angsi),R.drawable.family_son,R.raw.family_son))
        family.add(Word(getString(R.string.daughter),
            getString(R.string.tune),R.drawable.family_daughter,R.raw.family_daughter))
        family.add(Word(getString(R.string.older_brother),
            getString(R.string.taachi),R.drawable.family_older_brother,R.raw.family_older_brother))
        family.add(Word(getString(R.string.younger_brother),
            getString(R.string.chalitti),R.drawable.family_younger_brother,R.raw.family_younger_brother))
        family.add(Word(getString(R.string.older_sister),
            getString(R.string.te_e),R.drawable.family_older_sister,R.raw.family_older_sister))
        family.add(Word(
            getString(R.string.younger_sister),
            getString(R.string.kolliti),R.drawable.family_younger_sister,R.raw.family_younger_sister))
        family.add(Word(getString(R.string.grandmother),
            getString(R.string.ama),R.drawable.family_grandmother,R.raw.family_grandmother))
        family.add(Word(getString(R.string.grandfather), getString(R.string.paapa),R.drawable.family_grandfather,R.raw.family_grandfather))

        // call the WordAdaptor class of type context, resource and MutableList
        val adaptor = WordAdaptor(requireActivity(),R.color.category_family, family)
        // call the Listview the ArrayAdaptor will recycle
        adaptor.audio = requireActivity().getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager
        val familyView = fam.findViewById<ListView>(R.id.list)
        // attach the ArrayAdaptor to the Listview
        familyView.adapter=adaptor

        return fam
    }
}