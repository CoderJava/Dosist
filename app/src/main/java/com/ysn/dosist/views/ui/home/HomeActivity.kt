/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 11:06 AM
 */

package com.ysn.dosist.views.ui.home

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.di.component.home.DaggerHomeActivityComponent
import com.ysn.dosist.di.module.home.HomeActivityModule
import com.ysn.dosist.views.base.BaseActivity
import com.ysn.dosist.views.ui.home.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.activity_home.*
import java.text.DecimalFormat
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadBalanceCurrent()
        presenter.onLoadTransactionDetail()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        text_view_overview_activity_main.text = resultBalanceCurrent.balance.toString()
        val decimalFormat = DecimalFormat("#,###")
        val incomeFormat = decimalFormat.format(resultBalanceCurrent.income)
        val expenseFormat = decimalFormat.format(resultBalanceCurrent.expense)
        text_view_income_activity_main.text = getString(R.string.income_format, incomeFormat)
        text_view_expense_activity_main.text = getString(R.string.expense_format, expenseFormat)
    }

    override fun getViewContext(): Context = this

    override fun loadTransactionDetail(adapterTransactionDetail: AdapterTransactionDetail) {
        recycler_view_transaction_detail_activity_main.layoutManager = LinearLayoutManager(this)
        recycler_view_transaction_detail_activity_main.adapter = adapterTransactionDetail
        if (adapterTransactionDetail.itemCount == 0) {
            progress_bar_activity_home.visibility = View.VISIBLE
            recycler_view_transaction_detail_activity_main.visibility = View.INVISIBLE
        } else {
            progress_bar_activity_home.visibility = View.GONE
            recycler_view_transaction_detail_activity_main.visibility = View.GONE
        }
    }
}
