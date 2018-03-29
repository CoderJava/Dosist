/*
 * Created by YSN Studio on 3/29/18 10:39 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/29/18 10:36 PM
 */

package com.ysn.dosist.di.component.fragments.filterhistory

import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.fragments.filterhistory.FilterHistoryTransactionBottomSheetDialogFragmentModule
import com.ysn.dosist.views.ui.fragments.filterhistory.FilterHistoryTransactionBottomSheetDialogFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FilterHistoryTransactionBottomSheetDialogFragmentModule::class)])
interface FilterHistoryTransactionBottomSheetDialogFragmentComponent {

    fun inject(filterHistoryTransactionBottomSheetDialogFragment: FilterHistoryTransactionBottomSheetDialogFragment)

}