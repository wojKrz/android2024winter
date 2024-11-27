package edu.programmingclasses2024winter

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkApi {
  private val client = OkHttpClient()

  fun getPosts(): String {

    val request = Request.Builder()
      .url("https://jsonplaceholder.typicode.com/posts")
      .build()

    return client.newCall(request).execute()
      .body?.string() ?: "Error"
  }

  fun getPost(id: String) {
    val request = Request.Builder()
      .url("https://jsonplaceholder.typicode.com/posts/$id")
      .build()

    client.newCall(request).execute()
      .body?.string()?.let { println(it) }

  }
}
