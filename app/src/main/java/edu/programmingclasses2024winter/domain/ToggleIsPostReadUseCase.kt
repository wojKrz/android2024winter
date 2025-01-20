package edu.programmingclasses2024winter.domain

import edu.programmingclasses2024winter.Post
import edu.programmingclasses2024winter.PostRepository
import javax.inject.Inject

class ToggleIsPostReadUseCase @Inject constructor(
  private val repository: PostRepository
) {

  suspend operator fun invoke(post: Post) {
    val flippedPost = post.copy(isRead = post.isRead.not())
    repository.upsert(flippedPost)
  }
}
