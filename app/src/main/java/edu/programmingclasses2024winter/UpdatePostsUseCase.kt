package edu.programmingclasses2024winter

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class UpdatePostsUseCase(
  private val dispatcher: CoroutineDispatcher,
  private val postsApi: PostsApi,
  private val postsRepository: PostRepository
) {
  suspend operator fun invoke(): OperationResult<Unit> {
    return withContext(dispatcher) {
      try {
        postsApi.getPosts()
          .apply { postsRepository.upsert(this) }
        OperationResult.Success(Unit)
      } catch (e: UnknownHostException) {
        return@withContext OperationResult.Failure<Unit>(e)
      }
    }
  }
}
