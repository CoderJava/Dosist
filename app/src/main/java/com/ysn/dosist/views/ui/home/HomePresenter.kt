/*
 * Created by YSN Studio on 3/18/18 10:38 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 10:29 PM
 */

package com.ysn.dosist.views.ui.home

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.ui.home.adapter.AdapterTransactionDetail
import io.objectbox.android.AndroidScheduler
import io.objectbox.reactive.DataSubscription
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/16/18.
 */
class HomePresenter @Inject constructor(private var dbManager: DbManager) : BasePresenter<HomeView>(), AnkoLogger {

    private lateinit var adapterTransactionDetail: AdapterTransactionDetail
    private lateinit var transactionDetailSubscription: DataSubscription
    private lateinit var detailTransactions: ArrayList<DetailTransaction>

    fun onLoadBalanceCurrent() {
        val resultBalanceCurrent = dbManager.queryBalanceCurrentBox()
        view?.loadBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

    fun onLoadTransactionDetail() {
        val context = view?.getViewContext()
        detailTransactions = ArrayList()
        adapterTransactionDetail = AdapterTransactionDetail(
                context = context!!,
                dbManager = dbManager,
                detailTransactions = detailTransactions
        )
        transactionDetailSubscription = dbManager.queryGetAllTransactionBox()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction>? ->
                    this.detailTransactions.clear()
                    this.detailTransactions = detailTransactions as ArrayList<DetailTransaction>
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.loadTransactionDetail(adapterTransactionDetail = adapterTransactionDetail)
                }
    }

    fun onRefreshBalanceCurrent() {
        val resultBalanceCurrent = dbManager.queryBalanceCurrentBox()
        view?.refreshBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

    fun onRefreshTransactionDetail() {
        val context = view?.getViewContext()
        transactionDetailSubscription = dbManager.queryGetAllTransactionBox()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction> ->
                    this.detailTransactions.addAll(detailTransactions)
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.refreshTransactionDetail()
                }
    }

}