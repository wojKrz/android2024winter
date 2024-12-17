package edu.programmingclasses2024winter.usecases

import edu.programmingclasses2024winter.network.NetworkApi
import edu.programmingclasses2024winter.PostRepository
import java.net.UnknownHostException
import javax.inject.Inject

class DownloadPostsUseCase @Inject constructor(
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
