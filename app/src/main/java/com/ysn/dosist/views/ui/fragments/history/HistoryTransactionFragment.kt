/*
 * Created by YSN Studio on 3/25/18 2:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 1:57 PM
 */

package com.ysn.dosist.views.ui.fragments.history


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R
import com.ysn.dosist.di.component.fragments.history.DaggerHIstoryTransactionFragmentComponent
import com.ysn.dosist.di.module.fragments.history.HistoryTransactionFragmentModule
import com.ysn.dosist.views.base.BaseFragment
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.fragment_history_transaction.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HistoryTransactionFragment : BaseFragment(), HistoryTransactionView {

    private lateinit var title: String
    private var page: Int = 0

    @Inject
    lateinit var presenter: HistoryTransactionPresenter

    companion object {
        fun newInstance(page: Int, title: String): HistoryTransactionFragment {
            val historyTransactionFragment = HistoryTransactionFragment()
            val bundle = Bundle()
            bundle.putInt("page", page)
            bundle.putString("title", title)
            return historyTransactionFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEventBus()
        presenter.onInitFilter()
        presenter.onLoadHistoryTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterEventBus()
    }

    private fun unregisterEventBus() {
        EventBus.getDefault().unregister(this)
    }

    private fun registerEventBus() {
        EventBus.getDefault().register(this)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHIstoryTransactionFragmentComponent.builder()
                .appComponent(getAppComponent())
                .historyTransactionFragmentModule(HistoryTransactionFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun initFilter(filter: String) {
        text_view_value_filter_fragment_history_transaction.text = filter
    }

    override fun getViewContext(): Context? = context

    override fun loadHistoryTransaction (adapterTransactionDetail: AdapterTransactionDetail) {
        recycler_view_history_transaction_fragment_history_transaction.layoutManager = LinearLayoutManager(context)
        recycler_view_history_transaction_fragment_history_transaction.adapter = adapterTransactionDetail
        if (adapterTransactionDetail.itemCount == 0) {
            progress_bar_fragment_history_transaction.visibility = View.GONE
            recycler_view_history_transaction_fragment_history_transaction.visibility = View.INVISIBLE
            lottie_animation_view_empty_box_fragment_history_transaction.visibility = View.VISIBLE
        } else {
            progress_bar_fragment_history_transaction.visibility = View.GONE
            recycler_view_history_transaction_fragment_history_transaction.visibility = View.VISIBLE
            lottie_animation_view_empty_box_fragment_history_transaction.visibility = View.GONE
        }
    }

    @Subscribe
    fun onMessageEvent(mapData: HashMap<String, Any>) {
        val fromClass = mapData["fromClass"].toString().toLowerCase()
        /* I don't know what I'm doing in here */
    }

}
