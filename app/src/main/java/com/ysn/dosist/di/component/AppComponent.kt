/*
 * Created by YSN Studio on 3/23/18 11:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:52 PM
 */

package com.ysn.dosist.di.component

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ysn.dosist.api.Endpoints
import com.ysn.dosist.di.module.*
import dagger.Component
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Singleton
@Component(modules = [(AppModule::class), (RetrofitModule::class), (ApiModule::class), (OkHttpModule::class), (SharedPreferencesModule::class)])
interface AppComponent {

    fun application(): Application

    fun gson(): Gson

    fun retrofit(): Retrofit

    fun endpoints(): Endpoints

    fun cache(): Cache

    fun client(): OkHttpClient

    fun loggingInterceptor(): HttpLoggingInterceptor

    fun sharedPreferences(): SharedPreferences

}