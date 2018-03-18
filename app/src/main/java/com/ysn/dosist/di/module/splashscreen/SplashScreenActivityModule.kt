/*
 * Created by YSN Studio on 3/18/18 4:51 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:29 PM
 */

package com.ysn.dosist.di.module.splashscreen

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.views.ui.splashscreen.SplashScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/18/18.
 */
@Module
class SplashScreenActivityModule {

    @Provides
    @ActivityScope
    internal fun provideSplashScreenPresenter(dbManager: DbManager): SplashScreenPresenter = SplashScreenPresenter(dbManager = dbManager)

}