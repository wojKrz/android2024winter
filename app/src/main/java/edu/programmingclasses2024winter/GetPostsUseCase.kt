package edu.programmingclasses2024winter

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetPostsUseCase(
  private val dispatcher: CoroutineDispatcher,
  private val postsApi: PostsApi
) {
  suspend operator fun invoke(): List<Post> {
    return withContext(dispatcher) {
      postsApi.getPosts()
    }
  }
}
