/*
 * Created by YSN Studio on 3/30/18 6:41 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 11:52 AM
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
import com.ysn.dosist.views.ui.fragments.filterhistory.FilterHistoryTransactionBottomSheetDialogFragment
import com.ysn.dosist.views.ui.fragments.home.adapter.AdapterTransactionDetail
import kotlinx.android.synthetic.main.fragment_history_transaction.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HistoryTransactionFragment : BaseFragment(), HistoryTransactionView, View.OnClickListener {

    private val TAG = javaClass.simpleName

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
        initListeners()
        initFilter()
        doLoadData()
    }

    private fun initFilter() {
        presenter.onInitFilter()
    }

    private fun doLoadData() {
        val monthYearFilter = text_view_value_filter_fragment_history_transaction.text.toString().trim().split(" ")
        val monthFilter = monthYearFilter[0]
        val yearFilter = monthYearFilter[1]
        presenter.onLoadHistoryTransaction(month = monthFilter, year = yearFilter)
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

    private fun initListeners() {
        linear_layout_container_filter_fragment_history_transaction.setOnClickListener(this)
    }

    override fun initFilter(filter: String) {
        text_view_value_filter_fragment_history_transaction.text = filter
    }

    override fun getViewContext(): Context? = context

    override fun loadHistoryTransaction(adapterTransactionDetail: AdapterTransactionDetail) {
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
        when (fromClass) {
            "filterhistorytransactionbottomsheetdialogfragment" -> {
                val monthYearFilter = mapData["monthYearFilter"].toString().split(" ")
                val monthFilter = monthYearFilter[0]
                val yearFilter = monthYearFilter[1].let { it.substring(2, it.length) }
                text_view_value_filter_fragment_history_transaction.text = StringBuilder("$monthFilter $yearFilter")
                presenter.onRefreshHistoryTransaction(month = monthFilter, year = yearFilter)
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.linear_layout_container_filter_fragment_history_transaction -> {
                val filterHistoryTransactionBottomSheetDialogFragment = FilterHistoryTransactionBottomSheetDialogFragment()
                val filter = text_view_value_filter_fragment_history_transaction.text.toString().trim().split(" ")
                val month = filter[0]
                val year = filter[1]
                val bundle = Bundle()
                bundle.putString("month", month)
                bundle.putString("year", year)
                filterHistoryTransactionBottomSheetDialogFragment.arguments = bundle
                filterHistoryTransactionBottomSheetDialogFragment.show(fragmentManager, TAG)
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    override fun refreshHistoryTransaction() {
        /* nothing to do in here */
    }

}
