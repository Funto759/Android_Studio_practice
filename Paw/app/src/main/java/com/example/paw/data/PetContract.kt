package com.example.paw.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.BaseColumns

 object PetContract {

 final const val CONTENT_URI ="com.example.paw"

   val Base_Content_Uri = Uri.parse("content://$CONTENT_URI")

 final const val PATH_PET = "pets"

 object PetEntry:BaseColumns{

  final val CONTENT_URI_MAIN =Uri.withAppendedPath(Base_Content_Uri, PATH_PET)

  final val CONTENT_LIST_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_URI + "/" + PATH_PET

  final val CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/"+ CONTENT_URI + "/"+ PATH_PET

   const val TABLE_NAME ="pets"
   const val COLUMN_ID = BaseColumns._ID
   const val COLUMN_NAME = "name"
   const val COLUMN_BREED = "breed"
   const val COLUMN_GENDER = "gender"
   const val COLUMN_WEIGHT = "weight"

   // possible value for the gender of the pet

   final const val GENDER_UNKNOWN:Int = 0
   final const val GENDER_MALE:Int = 1
   final const val GENDER_FEMALE:Int = 2

  fun validGender(gender:Int):Boolean{
   if (gender == GENDER_UNKNOWN || gender== GENDER_MALE || gender== GENDER_FEMALE){
    return true
   }else
    return false
  }



  }


}