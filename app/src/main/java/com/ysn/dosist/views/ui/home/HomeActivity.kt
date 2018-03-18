/*
 * Created by YSN Studio on 3/18/18 7:27 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 6:00 PM
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
import com.ysn.dosist.views.ui.addtransaction.AddTransactionActivity
import com.ysn.dosist.views.ui.home.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import java.text.DecimalFormat
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView, View.OnClickListener {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initListeners()
    }

    private fun initListeners() {
        floating_action_button_add_transaction_activity_home.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadBalanceCurrent()
        presenter.onLoadTransactionDetail()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.floating_action_button_add_transaction_activity_home -> startActivity(intentFor<AddTransactionActivity>())
            else -> {
                /** nothing to do in here */
            }
        }
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
        text_view_overview_activity_home.text = resultBalanceCurrent.balance.toString()
        val decimalFormat = DecimalFormat("#,###")
        val incomeFormat = decimalFormat.format(resultBalanceCurrent.income)
        val expenseFormat = decimalFormat.format(resultBalanceCurrent.expense)
        text_view_income_activity_home.text = getString(R.string.income_format, incomeFormat)
        text_view_expense_activity_home.text = getString(R.string.expense_format, expenseFormat)
    }

    override fun getViewContext(): Context = this

    override fun loadTransactionDetail(adapterTransactionDetail: AdapterTransactionDetail) {
        recycler_view_transaction_detail_activity_home.layoutManager = LinearLayoutManager(this)
        recycler_view_transaction_detail_activity_home.adapter = adapterTransactionDetail
        if (adapterTransactionDetail.itemCount == 0) {
            progress_bar_activity_home.visibility = View.VISIBLE
            recycler_view_transaction_detail_activity_home.visibility = View.INVISIBLE
        } else {
            progress_bar_activity_home.visibility = View.GONE
            recycler_view_transaction_detail_activity_home.visibility = View.GONE
        }
    }
}
