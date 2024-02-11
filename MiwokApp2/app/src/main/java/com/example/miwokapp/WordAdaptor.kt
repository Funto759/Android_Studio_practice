package com.example.miwokapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class WordAdaptor(context: Context, resource: Int, var item: MutableList<Word>):
ArrayAdapter<Word>(context, 0, item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null){
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.numers_layout, parent,false)
        }
        val currentWord :Word? = getItem(position)

        val englishTextView = listItemView!!.findViewById<TextView>(R.id.one)
        englishTextView.setText(currentWord!!.getDefaultTranslation())

        val miwokTranslation = listItemView!!.findViewById<TextView>(R.id.two)
        miwokTranslation.setText(currentWord!!.getMiwokTranslation())

        return listItemView
    }


}