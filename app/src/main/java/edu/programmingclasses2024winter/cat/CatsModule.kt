package edu.programmingclasses2024winter.cat

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.programmingclasses2024winter.R
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
abstract class CatsModule {

  @Binds
  abstract fun bindTigerToCat(tiger: Tiger): Cat

  companion object {

    @Provides
    @RoarSound
    fun provideRoarString(@ApplicationContext context: Context): String =
      context.resources.getString(R.string.tiger_roar)

    @Provides
    @MeowSound
    fun provideMeowSound(@ApplicationContext context: Context): String =
      context.resources.getString(R.string.meow)
  }
}

@Qualifier
annotation class RoarSound

@Qualifier
annotation class MeowSound
