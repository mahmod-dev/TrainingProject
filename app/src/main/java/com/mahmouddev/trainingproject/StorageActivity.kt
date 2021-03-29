package com.mahmouddev.trainingproject

import android.Manifest
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.PermissionRequest
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mahmouddev.trainingproject.databinding.ActivityStorageBinding
import com.mahmouddev.trainingproject.util.FilePathsExternal
import com.mahmouddev.trainingproject.util.ImageStorageInternal
import com.mahmouddev.trainingproject.util.MyPreferences
import java.io.File

class StorageActivity : AppCompatActivity() {
    lateinit var binding: ActivityStorageBinding
    val USERNAME = "username"
    val PASSWORD = "password"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  FilePathsExternal(this,"my folder","ffff").createFolder()
      //  requestPermissions()

        ImageStorageInternal.convertUrlToBitmap(this,"https://www.google.es/images/srpr/logo11w.png")
        showImage()
    }

    private fun sharedPref() {
        val edit = getSharedPreferences("my pref", MODE_PRIVATE).edit()
        val pref = getSharedPreferences("my pref", MODE_PRIVATE)

        binding.apply {

            btnSave.setOnClickListener {

                val username = etUserName.text.toString()
                val password = etPassword.text.toString()
                edit.putString(USERNAME, username)
                edit.putString(PASSWORD, password)
                edit.apply()


                val prefUserName = pref.getString(USERNAME, "not found")
                val prefPassword = pref.getString(PASSWORD, "password error")

                tvShow.text = "$prefUserName \n $prefPassword"

            }


            MyPreferences.context = this@StorageActivity
            MyPreferences.setStr("name", "mahmoud")
            MyPreferences.setInt("age", 50)
            MyPreferences.setBool("isLogin", true)
            val name = MyPreferences.getStr("name")


        }

    }

    private fun showImage() {
        Glide
            .with(this)
            .load("https://image.shutterstock.com/image-photo/white-transparent-leaf-on-mirror-260nw-1029171697.jpg")
          //  .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_launcher_foreground)
            .into(binding.img)
    }

    private fun requestPermissions(){
        Dexter.withContext(this@StorageActivity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )

            .withListener(object: MultiplePermissionsListener {

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if(report.areAllPermissionsGranted()){
                            Toast.makeText(this@StorageActivity,"تم الاذن بنجاح",Toast.LENGTH_LONG).show()
                        }else{
                            Toast.makeText(this@StorageActivity,"لم يتم الموافقة على جميع الاذونات",Toast.LENGTH_LONG).show()

                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    // Remember to invoke this method when the custom rationale is closed
                    // or just by default if you don't want to use any custom rationale.
                    token?.continuePermissionRequest()
                }
            })


            .withErrorListener {
                Log.e("TAG", "requestPermissions: ${it.name}", )
            }

            .check()
    }
}