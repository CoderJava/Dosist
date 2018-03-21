/*
 * Created by YSN Studio on 3/22/18 3:09 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:08 AM
 */

package com.ysn.dosist.views.ui.activities.dashboard

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.di.component.dashboard.DaggerDashboardActivityComponent
import com.ysn.dosist.di.module.dashboard.DashboardActivityModule
import com.ysn.dosist.views.base.BaseActivity
import com.ysn.dosist.views.ui.activities.addtransaction.AddTransactionActivity
import com.ysn.dosist.views.ui.activities.dashboard.adapter.DashboardPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class DashboardActivity : BaseActivity(), DashboardView, View.OnClickListener, AnkoLogger {

    @Inject
    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initListeners()
        initViews()
    }

    private fun initViews() {
        val dashboardPagerAdapter = DashboardPagerAdapter(fragmentManager = supportFragmentManager)
        view_pager_activity_home.adapter = dashboardPagerAdapter
    }

    private fun initListeners() {
        floating_action_button_add_transaction_activity_home.setOnClickListener(this)
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

    override fun getViewContext(): Context = this

}
