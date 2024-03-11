package com.example.paw

import android.R
import android.content.ContentUris
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.paw.adaptor.PetCursorAdaptor
import com.example.paw.data.PetContract
import com.example.paw.data.PetContract.PetEntry
import com.example.paw.data.PetReaderDpHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton




class CatalogActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<Cursor>{
    private var mHelper:PetReaderDpHelper? = null
    private var URI_LOADER = 0

    private lateinit var mAdaptor: PetCursorAdaptor
//    var adaptor = PetCursorAdaptor(this,Cursor)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.paw.R.layout.activity_catalog)

        val float = findViewById<FloatingActionButton>(com.example.paw.R.id.fab)
        float.setOnClickListener(View.OnClickListener {
            var intentt = Intent(this, EditActivity::class.java)
            startActivity(intentt)
        })

        val displayView = findViewById<ListView>(com.example.paw.R.id.list)
        val emptyView:View = findViewById(com.example.paw.R.id.empty_view)

        displayView.emptyView = emptyView

    displayView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
        var intent = Intent(this,EditActivity::class.java)

        var currentPetUri:Uri = ContentUris.withAppendedId(PetEntry.CONTENT_URI_MAIN,id)

        intent.data = currentPetUri

        startActivity(intent)
    })

    mAdaptor = PetCursorAdaptor(this,null)


    displayView.adapter = mAdaptor



// call the LoaderManager and assign an id
       //supportLoaderManager.initLoader(URI_LOADER,null,this)
  LoaderManager.getInstance(this).initLoader(URI_LOADER,null,this)


    }

    override fun onStart() {
        super.onStart()
        mAdaptor = PetCursorAdaptor(this,null)

        val displayView = findViewById<ListView>(com.example.paw.R.id.list)
        displayView.adapter = mAdaptor

        LoaderManager.getInstance(this).initLoader(URI_LOADER,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        var projection = arrayOf(PetEntry.COLUMN_ID,PetEntry.COLUMN_NAME,PetEntry.COLUMN_BREED)

        if (id==URI_LOADER){
            return CursorLoader(this,PetContract.PetEntry.CONTENT_URI_MAIN,projection,null,null,null)
        }else
            return throw IllegalArgumentException("error")

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        mAdaptor.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        mAdaptor.swapCursor(null)
    }

    private fun insertInfo(){



        var value:ContentValues = ContentValues()
        value.put(PetEntry.COLUMN_NAME,"Garfield")
        value.put(PetEntry.COLUMN_BREED,"Tabby")
        value.put(PetEntry.COLUMN_GENDER, PetEntry.GENDER_MALE)
        value.put(PetEntry.COLUMN_WEIGHT,7)


        val newUri:Uri? = contentResolver.insert(PetEntry.CONTENT_URI_MAIN,value)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.paw.R.menu.menu_catalog, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.example.paw.R.id.action_insert_dummy_data->{
                insertInfo()
                return true
            }
            com.example.paw.R.id.action_delete_all_entries -> {
                var discardButton:DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }
                showDeletePetDialog(discardButton)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDeletePetDialog(discardButton: DialogInterface.OnClickListener){

        var builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(com.example.paw.R.string.delete_all_dialog_msg)
        builder.setPositiveButton(com.example.paw.R.string.delete, DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null)
                contentResolver.delete(PetEntry.CONTENT_URI_MAIN,null,null)
        })
        builder.setNegativeButton(com.example.paw.R.string.cancel, DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null)
                dialog.dismiss()
        })
        var alertDialog = builder.create()
        alertDialog.show()

    }


}