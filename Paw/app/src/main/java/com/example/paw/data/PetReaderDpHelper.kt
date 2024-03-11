package com.example.paw.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.paw.data.PetContract.PetEntry

class PetReaderDpHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

        companion object{
            final val DATABASE_VERSION = 1
            final val DATABASE_NAME ="PetReader.db"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        val table = ("CREATE TABLE " + PetEntry.TABLE_NAME + "("
                + PetEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_BREED + " TEXT, "
                + PetEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + PetEntry.COLUMN_WEIGHT + " INTEGER NOT NULL DEFAULT 0" + ")")

        db!!.execSQL(table)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS" + PetEntry.TABLE_NAME)
        onCreate(db)
    }


}