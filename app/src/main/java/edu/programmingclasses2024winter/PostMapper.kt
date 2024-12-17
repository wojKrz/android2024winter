package edu.programmingclasses2024winter

import edu.programmingclasses2024winter.persistence.post.PostEntity

class PostMapper {

  fun mapPostToDatabaseEntity(post: Post): PostEntity = with(post) {
    PostEntity(userId, id, title, body, isRead)
  }

  fun mapDatabaseEntityToPost(entity: PostEntity): Post = with(entity) {
    Post(userId, id, title, body, isRead)
  }
}
