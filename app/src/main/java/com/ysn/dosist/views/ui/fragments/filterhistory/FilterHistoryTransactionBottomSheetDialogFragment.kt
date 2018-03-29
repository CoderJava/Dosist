/*
 * Created by YSN Studio on 3/29/18 10:39 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/29/18 10:38 PM
 */

package com.ysn.dosist.views.ui.fragments.filterhistory

import com.ysn.dosist.di.component.fragments.filterhistory.DaggerFilterHistoryTransactionBottomSheetDialogFragmentComponent
import com.ysn.dosist.di.module.fragments.filterhistory.FilterHistoryTransactionBottomSheetDialogFragmentModule
import com.ysn.dosist.views.base.BaseBottomSheetDialogFragment
import javax.inject.Inject

class FilterHistoryTransactionBottomSheetDialogFragment : BaseBottomSheetDialogFragment(), FilterHistoryTransactionView {

    @Inject
    lateinit var presenter: FilterHistoryTransactionPresenter

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerFilterHistoryTransactionBottomSheetDialogFragmentComponent.builder()
                .appComponent(getAppComponent())
                .filterHistoryTransactionBottomSheetDialogFragmentModule(FilterHistoryTransactionBottomSheetDialogFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}