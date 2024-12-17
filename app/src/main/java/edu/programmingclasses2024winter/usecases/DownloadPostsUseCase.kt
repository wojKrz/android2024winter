package edu.programmingclasses2024winter.usecases

import edu.programmingclasses2024winter.NetworkApi
import edu.programmingclasses2024winter.PostRepository
import java.net.UnknownHostException

class DownloadPostsUseCase(
  private val api: NetworkApi,
  private val repository: PostRepository
) {

  suspend operator fun invoke() {
    try {
      api.getPosts()
        .apply {
          repository.saveAllPosts(this)
        }
    } catch (e: UnknownHostException) {

    }
  }
}
