package com.example.rest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class GitHubRepoAdaptor(context: Context,resource : Int,var name:List<GitHubRepo>):
    ArrayAdapter<GitHubRepo>(context,0,name) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var list = convertView
        if (list == null){
            list = LayoutInflater.from(context).inflate(R.layout.repo,parent,false)
        }

        var name = getItem(position)

        val l = list!!.findViewById<TextView>(R.id.repo)
        l.setText(name!!.getName())

        return list
    }
}