/*
 * Created by YSN Studio on 3/18/18 4:50 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:29 PM
 */

package com.ysn.dosist.di.module.home

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.views.ui.home.HomePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    internal fun provideHomePresenter(dbManager: DbManager): HomePresenter = HomePresenter(dbManager = dbManager)

}