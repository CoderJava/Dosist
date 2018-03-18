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