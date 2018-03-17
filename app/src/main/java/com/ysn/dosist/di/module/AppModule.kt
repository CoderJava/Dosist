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
