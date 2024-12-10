package edu.programmingclasses2024winter

import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

  @GET("posts")
  suspend fun getPosts(): List<Post>

  @GET("posts/{id}")
  suspend fun getPost(@Path("id") id: String): Post
}
