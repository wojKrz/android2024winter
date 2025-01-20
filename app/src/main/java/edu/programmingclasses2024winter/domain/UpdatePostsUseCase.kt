package edu.programmingclasses2024winter.domain

import edu.programmingclasses2024winter.OperationResult
import edu.programmingclasses2024winter.OperationResult.Failure
import edu.programmingclasses2024winter.OperationResult.Success
import edu.programmingclasses2024winter.PostRepository
import edu.programmingclasses2024winter.net.PostsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject

class UpdatePostsUseCase @Inject constructor(
  private val dispatcher: CoroutineDispatcher,
  private val postsApi: PostsApi,
  private val postsRepository: PostRepository
) {
  suspend operator fun invoke(): OperationResult<Unit> {
    return withContext(dispatcher) {
      try {
        postsApi.getPosts()
          .apply { postsRepository.upsert(this) }
        Success(Unit)
      } catch (e: UnknownHostException) {
        return@withContext Failure<Unit>(e)
      }
    }
  }
}
