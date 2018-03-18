/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/17/18 7:48 AM
 */

package com.ysn.dosist.di.module

import android.app.Application
import com.google.gson.GsonBuilder
import com.ysn.dosist.db.DbManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideResource() = application.resources

    @Provides
    @Singleton
    fun provideDbManager() = DbManager(application)

}
