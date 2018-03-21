/*
 * Created by YSN Studio on 3/22/18 1:38 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:32 AM
 */

package com.ysn.dosist.di.module.dashboard

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.views.ui.dashboard.DashboardPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/16/18.
 */
@Module
class DashboardActivityModule {

    @Provides
    @ActivityScope
    internal fun provideHomePresenter(dbManager: DbManager): DashboardPresenter = DashboardPresenter(dbManager = dbManager)

}