package com.mahmouddev.trainingproject.retrofit

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): PostsResponse

    @GET("posts/{id}")
    suspend fun getPostsById(@Path("id") id: Int): PostsResponseItem

    @GET("comments")
    suspend fun getComments(
        @Query("postId") postId: Int,
        @Query("email") email: String,
    ): CommentsResponse

    @FormUrlEncoded
    @POST("posts")
    suspend fun createPost(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): PostsResponseItem


    @POST("posts")
    suspend fun createPostObj(
        @Body item: PostsResponseItem
    ): PostsResponseItem

}