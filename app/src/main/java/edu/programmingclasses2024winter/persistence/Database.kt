package edu.programmingclasses2024winter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.programmingclasses2024winter.persistence.post.PostDao
import edu.programmingclasses2024winter.persistence.post.PostEntity

@Database(
  version = 1,
  entities = [
    PostEntity::class
  ]
)
abstract class Database : RoomDatabase() {

  abstract fun postDao(): PostDao
}
