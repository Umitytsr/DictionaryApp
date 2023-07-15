package com.umitytsr.dictionaryapp.di

import com.umitytsr.dictionaryapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.umitytsr.dictionaryapp.BuildConfig
import com.umitytsr.dictionaryapp.data.service.MeaningApiService
import com.umitytsr.dictionaryapp.data.service.SynonymsApiService
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.BODY
            } else {
                this.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("meaning_retrofit")
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_MEANING)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryScopeService(@Named("meaning_retrofit") retrofit: Retrofit): MeaningApiService {
        return retrofit.create(MeaningApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("synonyms_retrofit")
    fun provideRetrofit2(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_SYNONYMS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryScopeService2(@Named("synonyms_retrofit") retrofit: Retrofit): SynonymsApiService {
        return retrofit.create(SynonymsApiService::class.java)
    }
}