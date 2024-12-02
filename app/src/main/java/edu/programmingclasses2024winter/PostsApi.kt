package edu.programmingclasses2024winter

import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

  @GET("posts")
  suspend fun makeNetworkCall(): String

  @GET("posts/{postId}")
  suspend fun makeAnotherFunctionCall(@Path("postId") postId: String): Post
}
