package edu.programmingclasses2024winter

import edu.programmingclasses2024winter.persistence.posts.PostDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository @Inject constructor(
  private val postsDao: PostDao,
  private val postMapper: PostMapper
) {

  fun getAllPosts(): Flow<List<Post>> =
    postsDao.getAllPosts()
      .map { databasePosts ->
        databasePosts.map(postMapper::mapDatabaseEntityToPost)
      }

  suspend fun upsert(posts: List<Post>) =
    posts.map(postMapper::mapPostToDatabaseEntity)
      .let { postsDao.insert(it) }

  suspend fun upsert(post: Post) =
    post.run(postMapper::mapPostToDatabaseEntity)
      .let { postsDao.insert(it) }
}
