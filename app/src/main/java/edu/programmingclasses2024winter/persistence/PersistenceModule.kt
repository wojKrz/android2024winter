package edu.programmingclasses2024winter.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.programmingclasses2024winter.persistence.post.PostDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext applicationContext: Context): Database =
    Room.databaseBuilder(applicationContext, Database::class.java, "database.db")
      .fallbackToDestructiveMigration()
      .fallbackToDestructiveMigrationOnDowngrade()
      .build()

  @Provides
  @Singleton
  fun provideDao(database: Database): PostDao =
    database.postDao()
}
