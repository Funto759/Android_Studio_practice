package com.example.miwokapp

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

// create a Word adaptor class serving as a subclass of the ArrayAdaptor class
class WordAdaptor(context: Context, resource: Int, var item: MutableList<Word> ):
ArrayAdapter<Word>(context, 0, item,) {
    val mColour = resource
    // create a media variable of Data type Mediaplayer
    var media:MediaPlayer? = null
    //Clean up the media player by releasing its resources.
    fun releaseMedia(){
        // If the media player is not null, then it may be currently playing a sound.
        if (media != null) {
            // release its resources because we no longer need it.
            media!!.release()
            // Set the media player back to null. For our code, we've decided tha
            // is not configured to play an audio file at the moment.
            media = null
        }

    }

    val mOnComplete = OnCompletionListener{
        releaseMedia()
    }

    // override the method @param getView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        //create the variable @param listView to hold the //object  convert view
        var listItemView = convertView
        if (listItemView == null){
            // inflate and use the @param numers_layout as the objects of the ListView
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.numers_layout, parent,false)
        }
        // get the position of the object whose id is called
        val currentWord :Word? = getItem(position)



        //adds the object of the View into the variable @ param englishTextView
        val englishTextView = listItemView!!.findViewById<TextView>(R.id.one)
        // gets the position of the viw and sets the called text into it
        englishTextView.setText(currentWord!!.getDefaultTranslation())

        //adds the object of the View into the variable @ param miwokTranslation
        val miwokTranslation = listItemView!!.findViewById<TextView>(R.id.two)
        // gets the position of the viw and sets the called text into it
        miwokTranslation.setText(currentWord!!.getMiwokTranslation())

        //adds the object of the View into the variable @ param imageResource
        val imageResource = listItemView!!.findViewById<ImageView>(R.id.image)
        // gets the position of the viw and sets the called text into it
        imageResource.setImageResource(currentWord!!.getImage())

        //adds the ListView object into the Linear layout of variable @param list
        val list = listItemView!!.findViewById<LinearLayout>(R.id.text)
        // gets the color from the resource variable in the constructor and adds it into the
        // Recyclable views
        val color = ContextCompat.getColor(context,mColour)
        // set background color of variable color on the list variable
        list.setBackgroundColor(color)


       // adds the ListView object into the Linear layout of variable @param audio

       val audio = listItemView!!.findViewById<LinearLayout>(R.id.text)
        // sets an onClickListener on the audio variable
        audio.setOnClickListener(View.OnClickListener {

            releaseMedia()

            // assign the context and position of the item
            // and  the audio file from the Word class
            media= MediaPlayer.create(context,currentWord!!.getAudio())

            media!!.start()
            media!!.setOnCompletionListener(mOnComplete)
        })

        // returns a Listview with all the components added to it
        return listItemView
    }


}