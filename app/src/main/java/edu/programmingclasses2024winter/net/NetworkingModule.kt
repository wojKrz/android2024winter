package edu.programmingclasses2024winter.net

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {

  @Singleton
  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().also {
      it.level = HttpLoggingInterceptor.Level.BODY
    }

  @Singleton
  @Provides
  fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

  @Singleton
  @Provides
  fun provideRetrofit(netClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(netClient)
    .build()

  @Singleton
  @Provides
  @GoogleRetrofit
  fun provideGoogleRetrofit(netClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl("https://google.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(netClient)
    .build()

  @Singleton
  @Provides
  fun providePostsApi(retrofit: Retrofit): PostsApi =
    retrofit.create(PostsApi::class.java)
}

@Qualifier
annotation class GoogleRetrofit
