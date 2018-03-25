/*
 * Created by YSN Studio on 3/25/18 11:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 11:57 AM
 */

package com.ysn.dosist.views.ui.fragments.history


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R
import com.ysn.dosist.di.component.fragments.history.DaggerHIstoryTransactionFragmentComponent
import com.ysn.dosist.di.module.fragments.history.HistoryTransactionFragmentModule
import com.ysn.dosist.views.base.BaseFragment
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

}
