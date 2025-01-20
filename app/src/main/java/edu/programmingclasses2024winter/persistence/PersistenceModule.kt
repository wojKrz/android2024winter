package edu.programmingclasses2024winter.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.programmingclasses2024winter.persistence.posts.PostDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext applicationContext: Context): MyDatabase = Room.databaseBuilder(applicationContext, MyDatabase::class.java, "my_database.db")
    .fallbackToDestructiveMigration()
    .fallbackToDestructiveMigrationOnDowngrade()
    .build()

  @Singleton
  @Provides
  fun providePostsDao(database: MyDatabase): PostDao = database.getPostsDao()
}
