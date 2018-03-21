/*
 * Created by YSN Studio on 3/22/18 3:11 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:05 AM
 */

package com.ysn.dosist.views.ui.fragments.home

import android.content.Context
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail

/**
 * Created by yudisetiawan on 3/22/18.
 */
interface HomeView : BaseView {

    fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent)

    fun getViewContext(): Context?

    fun loadTransactionCurrent(adapterTransactionDetail: AdapterTransactionDetail)

    fun refreshBalanceCurrent(resultBalanceCurrent: BalanceCurrent)

    fun refreshTransactionDetail()
}