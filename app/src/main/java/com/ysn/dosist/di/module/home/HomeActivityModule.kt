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
    internal fun provideHomePresenter(api: DbManager): HomePresenter = HomePresenter(api)

}