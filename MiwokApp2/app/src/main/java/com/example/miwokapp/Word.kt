package com.example.miwokapp
// create an object to hold the data of the Arraylist by serving as its data type
data class Word(
    var mdefaultTranslation: String,
    var mMiwokTranslation: String,
    var mImage:Int, var mAudio:Int) {

    var a = mdefaultTranslation
    var b  = mMiwokTranslation
    var c = mImage
    var d = mAudio
    // returns the english translation from the ArrayList
    fun getDefaultTranslation(): String {
        return a
    }
    // returns the miwok translation from the ArrayList
    fun getMiwokTranslation(): String {
        return b
    }
    // returns the image resource from the ArrayList
    fun getImage():Int{
        return c
    }
    // returns the audio resource from the ArrayList
    fun getAudio():Int{
        return d
    }
}

