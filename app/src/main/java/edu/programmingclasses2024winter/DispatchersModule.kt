package edu.programmingclasses2024winter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class DispatchersModule {

  @Provides
  fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
