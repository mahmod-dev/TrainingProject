package com.mahmouddev.trainingproject.retrofit

interface ApiHelper {

    suspend fun getPosts(): PostsResponse

    suspend fun getPostsById(id: Int): PostsResponseItem

    suspend fun getComments(postId: Int, email: String): CommentsResponse


    suspend fun createPost(
        userId: Int,
        id: Int,
        title: String,
        body: String,
    ): PostsResponseItem


    suspend fun createPostObj(item: PostsResponseItem): PostsResponseItem

}