/*
 * Created by YSN Studio on 3/30/18 6:41 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 11:29 AM
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

    fun refreshHistoryTransaction()
}