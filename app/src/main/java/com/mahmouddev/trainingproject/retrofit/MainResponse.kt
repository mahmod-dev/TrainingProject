package com.mahmouddev.trainingproject.retrofit

class PostsResponse : ArrayList<PostsResponseItem>()

data class PostsResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)



class CommentsResponse : ArrayList<CommentsResponseItem>()

data class CommentsResponseItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)


