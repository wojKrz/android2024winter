package edu.programmingclasses2024winter.usecases

import edu.programmingclasses2024winter.Post
import edu.programmingclasses2024winter.PostRepository

class ToggleIsPostReadUseCase(
  private val repository: PostRepository
) {

  suspend operator fun invoke(post: Post) {
    val modifiedPost = post.copy(isRead = post.isRead.not())

    repository.savePost(modifiedPost)
  }
}
