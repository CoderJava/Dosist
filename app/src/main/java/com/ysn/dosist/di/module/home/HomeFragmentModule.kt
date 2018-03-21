/*
 * Created by YSN Studio on 3/22/18 2:06 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:49 AM
 */

package com.ysn.dosist.di.module.home

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.views.ui.fragments.home.HomePresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/22/18.
 */
@Module
class HomeFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideHomePresenter(dbManager: DbManager): HomePresenter = HomePresenter(dbManager = dbManager)

}