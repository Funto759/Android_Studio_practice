package com.example.miwokapp

data class Word(var mdefaultTranslation: String, var mMiwokTranslation: String) {

    var a = mdefaultTranslation
    var b  = mMiwokTranslation
    fun getDefaultTranslation(): String {
        return a
    }

    fun getMiwokTranslation(): String {
        return b
    }
}

