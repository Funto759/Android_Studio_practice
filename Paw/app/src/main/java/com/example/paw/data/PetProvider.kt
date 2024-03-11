package com.example.paw.data

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText


class PetProvider: ContentProvider() {

    val PETS = 100
    val PET_ID = 101
    var mDbHelper: PetReaderDpHelper? = null

    private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(PetContract.CONTENT_URI, PetContract.PATH_PET, PETS)

        addURI(PetContract.CONTENT_URI, PetContract.PATH_PET + "/#", PET_ID)
    }



    override fun onCreate(): Boolean {

        mDbHelper = PetReaderDpHelper(context, null,)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var database:SQLiteDatabase = mDbHelper!!.readableDatabase
        var cursor:Cursor

        var match = sUriMatcher.match(uri)
        
        if (match == PETS) {
           cursor=database.query(PetContract.PetEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder)

        } else if (match == PET_ID) {
           var selection = PetContract.PetEntry.COLUMN_ID + "=?"
            var selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
            cursor=database.query(PetContract.PetEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder)

        } else {
            throw IllegalArgumentException("Cannot query unkown URI" + uri)

        }


        cursor.setNotificationUri(context!!.contentResolver,PetContract.PetEntry.CONTENT_URI_MAIN)

        return cursor
    }


    override fun getType(uri: Uri): String? {
        var match = sUriMatcher.match(uri)
        if (match==PETS){
            return PetContract.PetEntry.CONTENT_LIST_TYPE
        }else if (match==PET_ID){
            return PetContract.PetEntry.CONTENT_ITEM_TYPE
        }else
            throw IllegalArgumentException("Unknown Uri $uri with match $match")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        var match = sUriMatcher.match(uri)
        if (match == PETS){
           return insertPet(uri,values)
        }else
            throw IllegalArgumentException("insertion is not supported for $uri")


    }

    fun insertPet(uri: Uri,values: ContentValues?): Uri? {

        var name = values!!.getAsString(PetContract.PetEntry.COLUMN_NAME)
        if (name == null) {
            throw IllegalArgumentException("Pet requires name")
        }

        var gender = values.getAsInteger(PetContract.PetEntry.COLUMN_GENDER)
        if (gender == null || !PetContract.PetEntry.validGender(gender)) {
            throw IllegalArgumentException("Pet requires valid gender")
        }

        var weight = values.getAsInteger(PetContract.PetEntry.COLUMN_WEIGHT)
        if (weight != null && weight < 0) {
            throw IllegalArgumentException("Pet requires valid weight")
        }

        val db = mDbHelper!!.writableDatabase
        val id = db.insert(PetContract.PetEntry.TABLE_NAME, null, values)

        if (id == 0L) {
            return null
        } else
//        context?.contentResolver?.notifyChange(uri,null)


            context?.contentResolver?.notifyChange(PetContract.PetEntry.CONTENT_URI_MAIN,null)
            return ContentUris.withAppendedId(uri, id)
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var db = mDbHelper!!.writableDatabase

        var rowsDeleted:Int

        var match = sUriMatcher.match(uri)
        if (match==PETS){
             rowsDeleted = db.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionArgs)
        }else if (match==PET_ID){
            var selection = PetContract.PetEntry.COLUMN_ID + "=?"
            var selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
             rowsDeleted = db.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionArgs)
        }else
            throw IllegalArgumentException("Deletion is not supported for $uri")

        if (rowsDeleted != 0){
            context?.contentResolver?.notifyChange(PetContract.PetEntry.CONTENT_URI_MAIN,null)
        }
        return rowsDeleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var match = sUriMatcher.match(uri)
        if (match == PETS){
            return updatePet(uri,values,selection,selectionArgs)
        }else if (match==PET_ID){
           var selection = PetContract.PetEntry.COLUMN_ID +"=?"
            var selectionArgs = arrayOf(ContentUris.parseId(uri).toString())
            return updatePet(uri,values,selection,selectionArgs)
        }else
            throw IllegalArgumentException("update is not supported for $uri")
    }

    fun updatePet(uri: Uri,values: ContentValues?,selection: String?,selectionArgs: Array<out String>?): Int {
        if (values!!.containsKey(PetContract.PetEntry.COLUMN_NAME)){
            var name = values.getAsString(PetContract.PetEntry.COLUMN_NAME)
            if (name==null){
                throw IllegalArgumentException("Pet requires name")
            }

        }

        if (values.containsKey(PetContract.PetEntry.COLUMN_GENDER)) {
            var gender = values.getAsInteger(PetContract.PetEntry.COLUMN_GENDER)
            if (gender == null || !PetContract.PetEntry.validGender(gender) ) {
                throw IllegalArgumentException("Pet requires a gender")
            }
        }

        if (values.containsKey(PetContract.PetEntry.COLUMN_WEIGHT)){
            var weight = values.getAsInteger(PetContract.PetEntry.COLUMN_WEIGHT)
            if (weight != null && weight < 0){
                throw IllegalArgumentException("pet requires valid weight")
            }
        }

        if (values.size() == 0){
            return 0
        }
        var db = mDbHelper!!.writableDatabase

        var rowUpdated = db.update(PetContract.PetEntry.TABLE_NAME,values,selection,selectionArgs)

        if (rowUpdated != 0){
            context?.contentResolver?.notifyChange(PetContract.PetEntry.CONTENT_URI_MAIN,null)
        }
        return rowUpdated
    }

}