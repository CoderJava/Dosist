/*
 * Created by YSN Studio on 3/22/18 3:10 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 2:35 AM
 */

package com.ysn.dosist.views.ui.fragments.history


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.dosist.R


/**
 * A simple [Fragment] subclass.
 */
class HistoryTransactionFragment : Fragment() {

    private lateinit var title: String
    private var page: Int = 0

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

}// Required empty public constructor
