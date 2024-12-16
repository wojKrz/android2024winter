package edu.programmingclasses2024winter

class ToggleIsPostReadUseCase(
  private val repository: PostRepository
) {

  suspend operator fun invoke(post: Post) {
    val flippedPost = post.copy(isRead = post.isRead.not())
    repository.upsert(flippedPost)
  }
}
