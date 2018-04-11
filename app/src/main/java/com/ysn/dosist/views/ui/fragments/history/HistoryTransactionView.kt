/*
 * Created by YSN Studio on 4/12/18 3:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/12/18 3:55 AM
 */

package com.ysn.dosist.views.ui.fragments.history

import android.content.Context
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail

/**
 * Created by yudisetiawan on 3/25/18.
 */
interface HistoryTransactionView : BaseView {

    fun initFilter(filter: String)

    fun getViewContext(): Context?

    fun loadHistoryTransaction(adapterTransactionDetail: AdapterTransactionDetail)

    fun refreshHistoryTransaction(adapterTransactionDetail: AdapterTransactionDetail)
}