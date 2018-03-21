/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.di.module.addtransaction

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.views.ui.activities.addtransaction.AddTransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/18/18.
 */
@Module
class AddTransactionActivityModule {

    @Provides
    @ActivityScope
    internal fun provideAddTransactionPresenter(dbManager: DbManager): AddTransactionPresenter = AddTransactionPresenter(dbManager = dbManager)

}