package com.mahmouddev.trainingproject.retrofit

class ApiImp(private val apiService: ApiService) : ApiHelper {
    override suspend fun getPosts(): PostsResponse {
       return apiService.getPosts()
    }

    override suspend fun getPostsById(id: Int): PostsResponseItem {
        return apiService.getPostsById(id)
    }

    override suspend fun getComments(postId: Int, email: String): CommentsResponse {
        return apiService.getComments(postId, email)
    }

    override suspend fun createPost(
        userId: Int,
        id: Int,
        title: String,
        body: String
    ): PostsResponseItem {
        return apiService.createPost(userId, id, title, body)
    }

    override suspend fun createPostObj(item: PostsResponseItem): PostsResponseItem {
        return apiService.createPostObj(item)
    }
}