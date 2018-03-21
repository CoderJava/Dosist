/*
 * Created by YSN Studio on 3/22/18 3:33 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:32 AM
 */

package com.ysn.dosist.di.module.fragments.home

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