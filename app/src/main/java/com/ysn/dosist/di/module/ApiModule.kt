/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/17/18 12:07 AM
 */

package com.ysn.dosist.di.module

import com.ysn.dosist.api.Endpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideEndpoints(retrofit: Retrofit): Endpoints = retrofit.create(Endpoints::class.java)

}