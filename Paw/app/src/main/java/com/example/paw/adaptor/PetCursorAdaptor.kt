package com.example.paw.adaptor

import android.content.Context
import android.database.Cursor
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.paw.R
import com.example.paw.data.PetContract

class PetCursorAdaptor(context: Context?, c: Cursor?) : CursorAdapter(context, c) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.pet_item,parent,false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        var nameBody = view!!.findViewById<TextView>(R.id.pet_name)
        var breedBody = view.findViewById<TextView>(R.id.pet_breed)

        var name = cursor!!.getString(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_NAME))
        var breed = cursor.getString(cursor.getColumnIndexOrThrow(PetContract.PetEntry.COLUMN_BREED))

        if (TextUtils.isEmpty(breed)){
            breed = context!!.getString(R.string.unknown_breed)
        }

        nameBody.setText(name)
        breedBody.setText(breed)


    }
}