package edu.programmingclasses2024winter

import edu.programmingclasses2024winter.persistence.posts.PostEntity
import javax.inject.Inject

class PostMapper @Inject constructor() {
  fun mapPostToDatabaseEntity(post: Post): PostEntity = with(post) {
    PostEntity(
      id = id,
      userId = userId,
      title = title,
      body = body,
      isRead = isRead
    )
  }

  fun mapDatabaseEntityToPost(databasePost: PostEntity) = with(databasePost) {
    Post(
      id = id,
      userId = userId,
      title = title,
      body = body,
      isRead = isRead
    )
  }
}
