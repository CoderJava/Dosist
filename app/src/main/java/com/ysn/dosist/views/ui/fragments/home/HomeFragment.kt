/*
 * Created by YSN Studio on 3/25/18 1:59 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 1:44 PM
 */

package com.ysn.dosist.views.ui.fragments.home


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.di.component.fragments.home.DaggerHomeFragmentComponent
import com.ysn.dosist.di.module.fragments.home.HomeFragmentModule
import com.ysn.dosist.views.base.BaseFragment
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.AnkoLogger
import java.text.DecimalFormat
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), HomeView, AnkoLogger {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var title: String
    private var page: Int = 0

    companion object {
        fun newInstance(page: Int, title: String): HomeFragment {
            val homeFragment = HomeFragment()
            val bundle = Bundle()
            bundle.putInt("page", page)
            bundle.putString("title", title)
            return homeFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEventBus()
        presenter.onLoadBalanceCurrent()
        presenter.onLoadTransactionDetail()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterEventBus()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homeFragmentModule(HomeFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    fun registerEventBus() {
        EventBus.getDefault().register(this)
    }

    private fun unregisterEventBus() {
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onMessageEvent(mapData: HashMap<String, Any>) {
        val fromClass = mapData["fromClass"].toString().toLowerCase()
        when (fromClass) {
            "addtransactionactivity" -> {
                presenter.onRefreshBalanceCurrent()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    override fun getViewContext(): Context? = context

    override fun loadBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        setupBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

    override fun loadTransactionCurrent(adapterTransactionDetail: AdapterTransactionDetail) {
        recycler_view_transaction_detail_fragment_home.layoutManager = LinearLayoutManager(context)
        recycler_view_transaction_detail_fragment_home.adapter = adapterTransactionDetail
        if (adapterTransactionDetail.itemCount == 0) {
            progress_bar_fragment_home.visibility = View.GONE
            recycler_view_transaction_detail_fragment_home.visibility = View.INVISIBLE
            lottie_animation_view_empty_box_fragment_home.visibility = View.VISIBLE
        } else {
            progress_bar_fragment_home.visibility = View.GONE
            recycler_view_transaction_detail_fragment_home.visibility = View.VISIBLE
            lottie_animation_view_empty_box_fragment_home.visibility = View.GONE
        }
    }

    private fun setupBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        val decimalFormat = DecimalFormat("#,###")
        val balanceFormat = decimalFormat.format(resultBalanceCurrent.balance)
                .replace(",", ".")
        val incomeFormat = decimalFormat.format(resultBalanceCurrent.income)
                .replace(",", ".")
        val expenseFormat = decimalFormat.format(resultBalanceCurrent.expense)
                .replace(",", ".")
        text_view_overview_fragment_home.text = balanceFormat
        text_view_income_fragment_home.text = getString(R.string.income_format, incomeFormat)
        text_view_expense_fragment_home.text = getString(R.string.expense_format, expenseFormat)
    }

    override fun refreshBalanceCurrent(resultBalanceCurrent: BalanceCurrent) {
        setupBalanceCurrent(resultBalanceCurrent = resultBalanceCurrent)
    }

}
