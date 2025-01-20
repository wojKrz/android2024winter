package edu.programmingclasses2024winter

import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
abstract class FirstModule {

  @Binds
  abstract fun bindTigerToCat(tiger: Tiger): Cat

  companion object {
    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
//    @TigerA
    fun provideTigerA(resources: Resources): Tiger = Tiger(resources, "A")

//    @Provides
//    @TigerB
//    fun provideTigerB(resources: Resources): Tiger = Tiger(resources, "B")
  }
}

@Qualifier
annotation class TigerA

@Qualifier
annotation class TigerB
