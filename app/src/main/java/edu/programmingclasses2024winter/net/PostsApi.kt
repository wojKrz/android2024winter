package edu.programmingclasses2024winter.net

import edu.programmingclasses2024winter.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

  @GET("posts")
  suspend fun getPosts(): List<Post>

  @GET("posts/{postId}")
  suspend fun makeAnotherFunctionCall(@Path("postId") postId: String): Post
}
