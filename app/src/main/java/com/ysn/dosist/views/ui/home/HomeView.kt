/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 11:06 AM
 */

package com.ysn.dosist.views.ui.home

import android.content.Context
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.home.adapter.AdapterTransactionDetail

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface HomeView : BaseView {

    fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent)

    fun getViewContext(): Context

    fun loadTransactionDetail(adapterTransactionDetail: AdapterTransactionDetail)

}