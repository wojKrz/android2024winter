package edu.programmingclasses2024winter

import edu.programmingclasses2024winter.persistence.post.PostDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
  private val dao: PostDao,
  private val mapper: PostMapper
) {

  fun getAllPosts(): Flow<List<Post>> =
    dao.getAllPosts()
      .map { list -> list.map(mapper::mapDatabaseEntityToPost) }

  suspend fun savePost(post: Post) {
    val postEntity = post.run(mapper::mapPostToDatabaseEntity)
    dao.upsertPost(postEntity)
  }

  suspend fun saveAllPosts(posts: List<Post>) {
    val postEntities = posts.map { mapper.mapPostToDatabaseEntity(it) }
    dao.upsertAllPosts(postEntities)
  }

}
