package com.mahmouddev.trainingproject.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.collections.ArrayList

class FilePathsExternal(var context: Context, var folderName: String, var fileName: String) {
    val TAG = "FilePaths"

    fun createFileUri(): Uri {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/*")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/${folderName}/")
            }

            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
        }
        else setUriForPreQ()

    }


    fun getFileFromAppPath(): File {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/" + folderName + "/" + fileName)
    }


    private fun setUriForPreQ(): Uri {
        val dir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/" + folderName + "/" + fileName)
        return Uri.fromFile(dir)

    }

     fun createFolder() {

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), folderName)

        if (!file.exists()) {
            Log.e(TAG, "createFolder: ", )
            file.mkdir()
        }
    }



    fun saveBitmap(bmp: Bitmap) {
        createFolder()
        val bytes = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        val uri = createFileUri()
        val fo = FileOutputStream(uri.path)
        fo.write(bytes.toByteArray())
        fo.close()


    }


}