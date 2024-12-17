package edu.programmingclasses2024winter.persistence.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

  @Query("SELECT * FROM posts")
  fun getAllPosts(): Flow<List<PostEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertAllPosts(posts: List<PostEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsertPost(post: PostEntity)
}
