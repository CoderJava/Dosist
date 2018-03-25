/*
 * Created by YSN Studio on 3/25/18 11:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 11:56 AM
 */

package com.ysn.dosist.di.module.fragments.history

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.views.ui.fragments.history.HistoryTransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/25/18.
 */
@Module
class HistoryTransactionFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideHistoryTransactionPresenter(dbManager: DbManager): HistoryTransactionPresenter = HistoryTransactionPresenter(dbManager = dbManager)

}