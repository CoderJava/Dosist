/*
 * Created by YSN Studio on 3/25/18 2:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 1:52 PM
 */

package com.ysn.dosist.views.ui.fragments.history

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail
import io.objectbox.android.AndroidScheduler
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/25/18.
 */
class HistoryTransactionPresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<HistoryTransactionView>() {

    private lateinit var detailTransactions: ArrayList<DetailTransaction>
    private lateinit var adapterTransactionDetail: AdapterTransactionDetail

    fun onInitFilter() {
        val filter = SimpleDateFormat("MMM yy", Locale.US).format(Date())
        view?.initFilter(filter = filter)
    }

    fun onLoadHistoryTransaction() {
        val context = view?.getViewContext()
        detailTransactions = ArrayList()
        adapterTransactionDetail = AdapterTransactionDetail(
                context = context!!,
                dbManager = dbManager,
                detailTransactions = detailTransactions
        )
        dbManager.queryGetAllTransactionBox()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction>? ->
                    this.detailTransactions.clear()
                    this.detailTransactions = detailTransactions as ArrayList<DetailTransaction>
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.loadHistoryTransaction(adapterTransactionDetail = adapterTransactionDetail)
                }
    }

    fun onRefreshHistoryTransaction() {
        // TODO: do something in here (pending)
    }

}