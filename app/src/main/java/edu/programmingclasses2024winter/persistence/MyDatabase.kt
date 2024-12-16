package edu.programmingclasses2024winter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.programmingclasses2024winter.persistence.posts.PostDao
import edu.programmingclasses2024winter.persistence.posts.PostEntity

@Database(
  version = 1,
  entities = [
    PostEntity::class
  ]
)
abstract class MyDatabase : RoomDatabase() {

  abstract fun getPostsDao(): PostDao
}
