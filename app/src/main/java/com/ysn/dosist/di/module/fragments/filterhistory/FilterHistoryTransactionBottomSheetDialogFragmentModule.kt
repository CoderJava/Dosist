/*
 * Created by YSN Studio on 3/29/18 10:39 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/29/18 10:35 PM
 */

package com.ysn.dosist.di.module.fragments.filterhistory

import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.views.ui.fragments.filterhistory.FilterHistoryTransactionPresenter
import dagger.Module
import dagger.Provides

@Module
class FilterHistoryTransactionBottomSheetDialogFragmentModule {

    @Provides
    @FragmentScope
    internal fun provideFilterHistoryTransactionPresenter(): FilterHistoryTransactionPresenter = FilterHistoryTransactionPresenter()

}