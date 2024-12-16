package edu.programmingclasses2024winter.persistence.posts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class PostDao {

  @Query("SELECT * FROM posts")
  abstract fun getAllPosts(): Flow<List<PostEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(entities: List<PostEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract suspend fun insert(entities: PostEntity)
}
