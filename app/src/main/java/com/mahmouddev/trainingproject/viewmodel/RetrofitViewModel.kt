package com.mahmouddev.trainingproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmouddev.trainingproject.retrofit.ApiHelper
import com.mahmouddev.trainingproject.retrofit.CommentsResponse
import com.mahmouddev.trainingproject.retrofit.PostsResponse
import com.mahmouddev.trainingproject.retrofit.PostsResponseItem
import com.mahmouddev.trainingproject.roomDB.dbUtil.Resource
import kotlinx.coroutines.launch

class RetrofitViewModel(var apiHelper: ApiHelper) : ViewModel() {
    private val TAG = "RetrofitViewModel"
    private var post = MutableLiveData<Resource<PostsResponse>>()
    private var comments = MutableLiveData<Resource<CommentsResponse>>()
    private var createPost = MutableLiveData<Resource<PostsResponseItem>>()
    private var createPostObj = MutableLiveData<Resource<PostsResponseItem>>()

    fun fetchPosts() {

        viewModelScope.launch {
            post.postValue(Resource.loading(null))
            try {

                val myPost = apiHelper.getPosts()
                post.postValue(Resource.success(myPost))

            } catch (ex: Exception) {
                post.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchPostsEx: ${ex.message} ")
            }

        }

    }

    fun fetchComments(postId: Int, email: String) {

        viewModelScope.launch {
            comments.postValue(Resource.loading(null))
            try {
                comments.postValue(Resource.success(apiHelper.getComments(postId, email)))

            } catch (ex: Exception) {
                comments.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchComments: ${ex.message} ")
            }

        }

    }

    fun fetchCreatePost(userId: Int, id: Int, title: String, body: String) {

        viewModelScope.launch {
            createPost.postValue(Resource.loading(null))
            try {
                createPost.postValue(
                    Resource.success(
                        apiHelper.createPost(
                            userId,
                            id,
                            title,
                            body
                        )
                    )
                )

            } catch (ex: Exception) {
                createPost.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchCreatePost: ${ex.message} ")
            }

        }

    }

    fun fetchCreatePost(item: PostsResponseItem) {

        viewModelScope.launch {
            createPost.postValue(Resource.loading(null))
            try {
                createPost.postValue(Resource.success(apiHelper.createPostObj(item)))

            } catch (ex: Exception) {
                createPost.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchCreatePost: ${ex.message} ")
            }

        }

    }

    fun postAsLiveData(): LiveData<Resource<PostsResponse>> {
        return post
    }

    fun commentAsLiveData(): LiveData<Resource<CommentsResponse>> {
        return comments
    }

    fun createPostAsLiveData(): LiveData<Resource<PostsResponseItem>> {
        return createPost
    }

    fun createPostObjAsLiveData(): LiveData<Resource<PostsResponseItem>> {
        return createPostObj
    }
}