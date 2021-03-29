package com.mahmouddev.trainingproject.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileInputStream


class ImageStorageInternal {
    companion object {

        fun saveToInternalStorage(context: Context, bitmapImage: Bitmap, imageFileName: String): String {
            val fos = context.openFileOutput(imageFileName, Context.MODE_PRIVATE)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)

            return context.filesDir.absolutePath
        }

        fun getImageFromInternalStorage(context: Context, imageFileName: String): Bitmap? {
            val directory = context.filesDir
            val file = File(directory, imageFileName)
            return BitmapFactory.decodeStream(FileInputStream(file))
        }

        fun deleteImageFromInternalStorage(context: Context, imageFileName: String): Boolean {
            val dir = context.filesDir
            val file = File(dir, imageFileName)
            return file.delete()
        }

        fun convertUrlToBitmap(context: Context, url: String) {
        //"https://www.google.es/images/srpr/logo11w.png"
            Glide.with(context).asBitmap().load(url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(bitmap: Bitmap, @Nullable transition: Transition<in Bitmap>?) {

                        saveToInternalStorage(context, bitmap, "water.png")
                      //  FilePaths(context,"pla pla","pppppssssssss.png").saveBitmap(resource)
                    }

                    override fun onLoadCleared(@Nullable placeholder: Drawable?) {

                    }
                })

        }

    }
}