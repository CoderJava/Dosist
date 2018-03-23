/*
 * Created by YSN Studio on 3/23/18 11:01 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:48 PM
 */

package com.ysn.dosist.di.module.activities.welcome

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.model.SharedPreferencesDosist
import com.ysn.dosist.views.ui.activities.welcome.WelcomePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/23/18.
 */
@Module
class WelcomeActivityModule {

    @Provides
    @ActivityScope
    internal fun provideWelcomePresenter(dbManager: DbManager, sharedPreferencesDosist: SharedPreferencesDosist): WelcomePresenter = WelcomePresenter(dbManager = dbManager, sharedPreferencesDosist = sharedPreferencesDosist)

}