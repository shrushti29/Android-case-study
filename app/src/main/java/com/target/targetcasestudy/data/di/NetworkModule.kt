package com.target.targetcasestudy.data.di

import com.target.targetcasestudy.domain.api.source.DealApiKtx
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

internal const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideDealApi(): DealApiKtx = Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    //enabled for logging purpose, will not log the body for prod app
                    this.level = HttpLoggingInterceptor.Level.BODY
                }).build()
        ).addConverterFactory(GsonConverterFactory.create()).build().create(DealApiKtx::class.java)


}