package com.mahmouddev.trainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.trainingproject.retrofit.ApiImp
import com.mahmouddev.trainingproject.retrofit.ApiService
import com.mahmouddev.trainingproject.retrofit.RetrofitBuilder
import com.mahmouddev.trainingproject.roomDB.dbUtil.Status
import com.mahmouddev.trainingproject.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.trainingproject.viewmodel.RetrofitViewModel

class RetrofitCoroutineActivity : AppCompatActivity() {
    val TAG = "RetrofitCoroutineActivity"
    private lateinit var viewModel: RetrofitViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_coroutine)
        initViewModel()
        viewModel.fetchPosts()
        viewModel.fetchComments(1,"Nikita@garfield.biz")
        getPostsAsLiveData()
        commentAsLiveData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(null, ApiImp(RetrofitBuilder.apiService))
        ).get(RetrofitViewModel::class.java)

    }

    private fun getPostsAsLiveData(){
        viewModel.postAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    Log.e(TAG, "getPostsAsLiveData: ${it.data}", )
                }

                Status.ERROR -> {

                }
            }
        })
    }

    private fun commentAsLiveData(){
        viewModel.commentAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    Log.e(TAG, "commentAsLiveData: ${it.data}", )
                }

                Status.ERROR -> {

                }
            }
        })
    }
}