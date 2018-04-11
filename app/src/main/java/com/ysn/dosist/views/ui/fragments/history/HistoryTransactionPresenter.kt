/*
 * Created by YSN Studio on 4/12/18 3:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 4/12/18 3:55 AM
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
import kotlin.collections.ArrayList

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

    fun onLoadHistoryTransaction(month: String, year: String) {
        val context = view?.getViewContext()
        detailTransactions = ArrayList()
        adapterTransactionDetail = AdapterTransactionDetail(
                context = context!!,
                dbManager = dbManager,
                detailTransactions = detailTransactions
        )
        dbManager.queryGetAllTransactionByMonthAndYear(month = month, year = year)
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction>? ->
                    this.detailTransactions.clear()
                    this.detailTransactions = detailTransactions as ArrayList<DetailTransaction>
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.loadHistoryTransaction(adapterTransactionDetail = adapterTransactionDetail)
                }
    }

    fun onRefreshHistoryTransaction(month: String, year: String) {
        detailTransactions = ArrayList()
        dbManager.queryGetAllTransactionByMonthAndYear(month = month, year = year)
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer { detailTransactions: MutableList<DetailTransaction>? ->
                    this.detailTransactions.clear()
                    this.detailTransactions = detailTransactions as ArrayList<DetailTransaction>
                    adapterTransactionDetail.refresh(this.detailTransactions)
                    view?.refreshHistoryTransaction(adapterTransactionDetail = adapterTransactionDetail)
                }
    }

}