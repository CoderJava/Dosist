/*
 * Created by YSN Studio on 3/30/18 6:39 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 11:57 AM
 */

package com.ysn.dosist.views.ui.fragments.home

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail
import io.objectbox.android.AndroidScheduler
import io.objectbox.reactive.DataSubscription
import org.jetbrains.anko.AnkoLogger
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/22/18.
 */
class HomePresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<HomeView>(), AnkoLogger {

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
        val now = SimpleDateFormat("MMM yy", Locale.US).format(Date()).split(" ")
        val monthNow = now[0]
        val yearNow = now[1]
        transactionDetailSubscription = dbManager.queryGetAllTransactionByMonthAndYear(month = monthNow, year = yearNow)
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction>? ->
                    this.detailTransactions.clear()
                    this.detailTransactions = detailTransactions as ArrayList<DetailTransaction>
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.loadTransactionCurrent(adapterTransactionDetail = adapterTransactionDetail)
                }

    }

    fun onRefreshBalanceCurrent() {
        val resultBalanceCurrent = dbManager.queryBalanceCurrentBox()
        view?.refreshBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

}