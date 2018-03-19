/*
 * Created by YSN Studio on 3/20/18 2:22 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 12:47 AM
 */

package com.ysn.dosist.di.module.choosecategory

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.views.ui.choosecategory.ChooseCategoryPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by yudisetiawan on 3/20/18.
 */
@Module
class ChooseCategoryBottomSheetDialogFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideChooseCategoryPresenter(dbManager: DbManager): ChooseCategoryPresenter = ChooseCategoryPresenter(dbManager = dbManager)

}