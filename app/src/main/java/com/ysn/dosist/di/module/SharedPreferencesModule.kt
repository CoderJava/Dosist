/*
 * Created by YSN Studio on 3/23/18 11:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:42 PM
 */

package com.ysn.dosist.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yudisetiawan on 3/23/18.
 */
@Module
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences = application.getSharedPreferences("PREF_DOSIST", Context.MODE_PRIVATE)

}