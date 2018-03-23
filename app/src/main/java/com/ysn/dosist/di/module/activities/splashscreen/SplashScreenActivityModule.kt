/*
 * Created by YSN Studio on 3/23/18 11:02 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:57 PM
 */

package com.ysn.dosist.di.module.activities.splashscreen

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.model.SharedPreferencesDosist
import com.ysn.dosist.views.ui.activities.splashscreen.SplashScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/18/18.
 */
@Module
class SplashScreenActivityModule {

    @Provides
    @ActivityScope
    internal fun provideSplashScreenPresenter(dbManager: DbManager, sharedPreferencesDosist: SharedPreferencesDosist): SplashScreenPresenter = SplashScreenPresenter(dbManager = dbManager, sharedPreferencesDosist = sharedPreferencesDosist)

}