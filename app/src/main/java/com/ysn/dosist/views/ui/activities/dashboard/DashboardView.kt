/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.activities.dashboard

import android.content.Context
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.activities.dashboard.adapter.AdapterTransactionDetail

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface DashboardView : BaseView {

    fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent)

    fun getViewContext(): Context

    fun loadTransactionDetail(adapterTransactionDetail: AdapterTransactionDetail)

    fun refreshTransactionDetail()

    fun refreshBalanceCurrent(resultBalanceCurrent: BalanceCurrent)

}