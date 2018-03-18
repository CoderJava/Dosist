/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/16/18 10:07 PM
 */

package com.ysn.dosist.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Module
class OkHttpModule {

    private fun getBaseBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(1000 * 180, TimeUnit.MILLISECONDS)
                .readTimeout(1000 * 180, TimeUnit.MILLISECONDS)
                .writeTimeout(1000 * 180, TimeUnit.MILLISECONDS)
    }

    class CachingControlInterceptor : Interceptor {

        private val TAG = javaClass.simpleName

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val cacheControl = CacheControl.Builder()
                    .maxStale(1000 * 60, TimeUnit.MILLISECONDS)
                    .maxAge(1000 * 60, TimeUnit.MILLISECONDS)
                    .build()
            requestBuilder.cacheControl(cacheControl)
            val request = requestBuilder.build()
            val response = chain.proceed(request)
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=604800")
                    .build()
        }

    }

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache = Cache(application.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @Singleton
    fun provideCachingControlInterceptor(): CachingControlInterceptor = CachingControlInterceptor()

    @Provides
    @Singleton
    fun provideOkHttp(cache: Cache, loggingInterceptor: HttpLoggingInterceptor, cachingControlInterceptor: CachingControlInterceptor): OkHttpClient =
            getBaseBuilder(cache)
                    .addNetworkInterceptor(cachingControlInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()

}
