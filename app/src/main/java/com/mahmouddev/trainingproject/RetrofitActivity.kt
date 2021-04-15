package com.mahmouddev.trainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mahmouddev.trainingproject.retrofit.ApiService
import com.mahmouddev.trainingproject.retrofit.CommentsResponse
import com.mahmouddev.trainingproject.retrofit.PostsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    val TAG = "RetrofitActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val builder = Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val request = builder.create(ApiService::class.java)
        //  getPosts(request)
        //  getComments(request)
      //  createPost(request)
        createPostObject(request)


    }

    fun getPosts(request: ApiService) {
      /*  request.getPostsById(3).enqueue(object : Callback<PostsResponseItem> {
            override fun onResponse(
                call: Call<PostsResponseItem>,
                response: Response<PostsResponseItem>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<PostsResponseItem>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })*/
    }

    fun getComments(request: ApiService) {

      /*  request.getComments(1, "Nikita@garfield.biz").enqueue(object : Callback<CommentsResponse> {
            override fun onResponse(
                call: Call<CommentsResponse>,
                response: Response<CommentsResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<CommentsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })*/
    }

    fun createPost(request: ApiService) {
     /*   request.createPost(1, 2, "my title", "my body")
            .enqueue(object : Callback<PostsResponseItem> {
                override fun onResponse(
                    call: Call<PostsResponseItem>,
                    response: Response<PostsResponseItem>
                ) {
                    if (response.isSuccessful) {
                        Log.e(TAG, "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<PostsResponseItem>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })*/
    }

    fun createPostObject(request: ApiService) {
      /*  request.createPostObj(PostsResponseItem("my body2", 8, "My title2", 9))
            .enqueue(object : Callback<PostsResponseItem> {
                override fun onResponse(
                    call: Call<PostsResponseItem>,
                    response: Response<PostsResponseItem>
                ) {
                    if (response.isSuccessful) {
                        Log.e(TAG, "onResponse: ${response.body()}")
                    }
                }

                override fun onFailure(call: Call<PostsResponseItem>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }

            })*/
    }
}