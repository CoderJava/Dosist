/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.activities.dashboard

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.di.component.dashboard.DaggerDashboardActivityComponent
import com.ysn.dosist.di.module.dashboard.DashboardActivityModule
import com.ysn.dosist.views.base.BaseActivity
import com.ysn.dosist.views.ui.activities.addtransaction.AddTransactionActivity
import com.ysn.dosist.views.ui.activities.dashboard.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.activity_home_bak.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import java.text.DecimalFormat
import javax.inject.Inject

class DashboardActivity : BaseActivity(), DashboardView, View.OnClickListener, AnkoLogger {

    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_bak)
        initListeners()
        registerEventBus()
    }

    private fun registerEventBus() {
        EventBus.getDefault().register(this)
    }

    private fun unregisterEventBus() {
        EventBus.getDefault().unregister(this)
    }

    private fun initListeners() {
        floating_action_button_add_transaction_activity_home.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadBalanceCurrent()
        presenter.onLoadTransactionDetail()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterEventBus()
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
        DaggerDashboardActivityComponent.builder()
                .appComponent(getAppComponent())
                .dashboardActivityModule(DashboardActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        setupBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

    private fun setupBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        text_view_overview_activity_home.text = resultBalanceCurrent.balance.toString()
        val decimalFormat = DecimalFormat("#,###")
        val incomeFormat = decimalFormat.format(resultBalanceCurrent.income).replace(",", ".")
        val expenseFormat = decimalFormat.format(resultBalanceCurrent.expense).replace(",", ".")
        text_view_income_activity_home.text = getString(R.string.income_format, incomeFormat)
        text_view_expense_activity_home.text = getString(R.string.expense_format, expenseFormat)
    }

    override fun getViewContext(): Context = this

    override fun loadTransactionDetail(adapterTransactionDetail: AdapterTransactionDetail) {
        recycler_view_transaction_detail_activity_home.layoutManager = LinearLayoutManager(this)
        recycler_view_transaction_detail_activity_home.adapter = adapterTransactionDetail
        if (adapterTransactionDetail.itemCount == 0) {
            progress_bar_activity_home.visibility = View.GONE
            recycler_view_transaction_detail_activity_home.visibility = View.INVISIBLE
            lottine_animatin_view_empty_box_activity_home.visibility = View.VISIBLE
        } else {
            progress_bar_activity_home.visibility = View.GONE
            recycler_view_transaction_detail_activity_home.visibility = View.VISIBLE
            lottine_animatin_view_empty_box_activity_home.visibility = View.GONE
        }
    }

    override fun refreshBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        setupBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

    override fun refreshTransactionDetail() {
        // TODO: do something in here if needed
    }

    @Subscribe
    fun onMessageEvent(mapData: HashMap<String, Any>) {
        val fromClass = mapData["fromClass"].toString().toLowerCase()
        when (fromClass) {
            "AddTransactionDetail" -> {
                presenter.onRefreshBalanceCurrent()
                presenter.onRefreshTransactionDetail()
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

}
