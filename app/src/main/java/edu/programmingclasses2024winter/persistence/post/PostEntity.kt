package edu.programmingclasses2024winter.persistence.post

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
  @ColumnInfo(name = "user_id")
  val userId: Long,
  @PrimaryKey
  val id: Long,
  val title: String,
  val body: String,
  @ColumnInfo(name = "is_read")
  val isRead: Boolean
)
