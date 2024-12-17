package edu.programmingclasses2024winter.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkingModule {

  @Provides
  @Singleton
  fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().also {
      it.level = HttpLoggingInterceptor.Level.BODY
    }

  @Provides
  @Singleton
  fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()

  @Provides
  @Singleton
  fun provideRetrofit(netClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl("https://jsonplaceholder.typicode.com/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(netClient)
      .build()

  @Provides
  @Singleton
  fun provideNetworkApi(retrofit: Retrofit): NetworkApi =
    retrofit.create(NetworkApi::class.java)
}
