package com.example.paw

import android.content.ContentUris
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.paw.data.PetContract.PetEntry
import com.example.paw.data.PetReaderDpHelper
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates


class EditActivity : AppCompatActivity(),LoaderManager.LoaderCallbacks<Cursor> {
    private var mName: TextInputEditText? = null

    private var mBreed: TextInputEditText? = null

    private var mWeight: TextInputEditText? = null

    private var mGenderSpinner: Spinner? = null

    private var mGender = 0

    private var URI_LOADER = 0

    private  var currentPetUri:Uri? = null

    private var petHasChanged:Boolean? = null

    private var mTouchListener:OnTouchListener = View.OnTouchListener { v, event ->
        petHasChanged = true
        return@OnTouchListener false
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(com.example.paw.R.layout.activity_edit)

        var intent = intent
        currentPetUri = intent.data

        if (currentPetUri == null){
            setTitle(getString(R.string.editor_activity_title_new_pet))
            invalidateOptionsMenu()
        }else {
            setTitle(getString(R.string.editor_activity_title_edit_pet))

            supportLoaderManager.initLoader(URI_LOADER,null,this)
        }






//        LoaderManager.getInstance(this).initLoader(URI_LOADER,null,this)

        mName = findViewById(com.example.paw.R.id.enName)
        mBreed = findViewById(com.example.paw.R.id.enBreed)
        mWeight = findViewById(com.example.paw.R.id.enWeight)
        mGenderSpinner = findViewById(com.example.paw.R.id.spinner_gender)

        mName!!.setOnTouchListener(mTouchListener)
        mBreed!!.setOnTouchListener(mTouchListener)
        mWeight!!.setOnTouchListener(mTouchListener)
        mGenderSpinner!!.setOnTouchListener(mTouchListener)



        setupSpinner()

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        var projection = arrayOf(PetEntry.COLUMN_ID,PetEntry.COLUMN_NAME,PetEntry.COLUMN_BREED,PetEntry.COLUMN_GENDER,
            PetEntry.COLUMN_WEIGHT)

//        var intent:Intent = intent
//        currentPetUri = intent.data


//        return CursorLoader(this, currentPetUri!!,projection,null,null,null)

  return CursorLoader(this,currentPetUri!!,projection,null,null,null)


    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {

        if (data == null || data.getCount() < 1) {
            return
        }

        if (data.moveToFirst()){
            var name = data.getString(data.getColumnIndexOrThrow(PetEntry.COLUMN_NAME))
            var breed = data.getString(data.getColumnIndexOrThrow(PetEntry.COLUMN_BREED))
            var weigh= 0
            var weight = data.getString(data.getColumnIndexOrThrow(PetEntry.COLUMN_WEIGHT))
            var gender = data.getInt(data.getColumnIndexOrThrow(PetEntry.COLUMN_GENDER))

            mName!!.setText(name)
            mBreed!!.setText(breed)
            mWeight!!.setText(weight)

           if (gender == PetEntry.GENDER_MALE){
               mGenderSpinner!!.setSelection(1)
           }else if (gender == PetEntry.GENDER_FEMALE) {
               mGenderSpinner!!.setSelection(2)
           }else
               mGenderSpinner!!.setSelection(0)
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        mName!!.setText("");
        mBreed!!.setText("");
        mWeight!!.setText("");
        mGenderSpinner!!.setSelection(0); // Select "Unknown" gender
    }

    private fun setupSpinner() {

        val genderSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            com.example.paw.R.array.gender_options,
            android.R.layout.simple_spinner_item
        )

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mGenderSpinner!!.adapter = genderSpinnerAdapter

        mGenderSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selection = parent!!.getItemAtPosition(position) as String
                if (!TextUtils.isEmpty(selection)) {
                    if(selection.equals(getString(com.example.paw.R.string.gender_male))) {
                        mGender = PetEntry.GENDER_MALE
                    } else if (selection.equals(getString(com.example.paw.R.string.gender_female))) {
                        mGender = PetEntry.GENDER_FEMALE
                    } else
                        mGender = PetEntry.GENDER_UNKNOWN

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mGender = PetEntry.GENDER_UNKNOWN
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.paw.R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.example.paw.R.id.action_save -> {
                var intent:Intent = getIntent()
                var currentPetUri = intent.data
                savePet()
                finish()
                return true

            }

            com.example.paw.R.id.action_delete -> {
                var discardButton:DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }
                showDeletePetDialog(discardButton)
                return true
            }

            android.R.id.home -> {
                if (!petHasChanged!!){
                    NavUtils.navigateUpFromSameTask(this)
                    return true
                }else{
                    var discardButton:DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
                        NavUtils.navigateUpFromSameTask(this)
                    }
                    showUnsavedDialog(discardButton)
                    return true

                }

            }
        }
        return super.onOptionsItemSelected(item)

    }


    fun savePet() {
        var name = mName!!.text.toString().trim()
        var breed = mBreed!!.text.toString().trim()
        var weightString = mWeight!!.text.toString().trim()
        if (currentPetUri == null && TextUtils.isEmpty(name) && TextUtils.isEmpty(breed) && TextUtils.isEmpty(weightString))
            return


        var values: ContentValues = ContentValues().apply {
            put(PetEntry.COLUMN_NAME, name)
            put(PetEntry.COLUMN_BREED, breed)
            put(PetEntry.COLUMN_GENDER, mGender)
            var weight = 0
            if (!TextUtils.isEmpty(weightString)) {
                weight = Integer.parseInt(weightString);
            }
            put(PetEntry.COLUMN_WEIGHT, weight);
        }

        if (currentPetUri == null){
            val newUri: Uri? = contentResolver.insert(PetEntry.CONTENT_URI_MAIN, values)
            if (newUri == null) {
                Toast.makeText(
                    this,
                    getString(R.string.error_with_saving_pet_toast_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                Toast.makeText(this,
                    getString(R.string.pet_saved_with_id_toast_message, newUri), Toast.LENGTH_SHORT)
                    .show()
            }
        }else {
            var rowsAffected = contentResolver.update(currentPetUri!!,values,null,null)
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "Update of pet failed",
                    Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, "Update of Pet successful",
                    Toast.LENGTH_SHORT).show();
            }
        }
    }

    fun showUnsavedDialog(discardButton:DialogInterface.OnClickListener){

        var builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.unsaved_changes_dialog_msg)
        builder.setPositiveButton(R.string.discard, discardButton)
        builder.setNegativeButton(R.string.keep_editing, DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null)
                dialog.dismiss()
        })
        var alertDialog = builder.create()
        alertDialog.show()

    }
    fun showDeletePetDialog(discardButton:DialogInterface.OnClickListener){

        var builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.delete_dialog_msg)
        builder.setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null) {
                deletePet()
                finish()
            }
        })
        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, which ->
            if (dialog != null) {
                dialog.dismiss()
            }
        })
        var alertDialog = builder.create()
        alertDialog.show()

    }

    override fun onBackPressed() {

        if (petHasChanged == false){
            super.onBackPressed()
            return
        }else {
            var discardButton: DialogInterface.OnClickListener =
                DialogInterface.OnClickListener { dialog, which ->
                    finish()
                }
            showUnsavedDialog(discardButton)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (currentPetUri == null){
            val menuItem:MenuItem = menu!!.findItem(R.id.action_delete)
            menuItem.setVisible(false)
            return true
        }else {
            return super.onPrepareOptionsMenu(menu)
        }
    }

    fun deletePet(){
        if (currentPetUri != null){
            contentResolver.delete(currentPetUri!!,null,null)
           Toast.makeText(this,"Pet has been Deleted",Toast.LENGTH_SHORT).show()
            }
        }


}


