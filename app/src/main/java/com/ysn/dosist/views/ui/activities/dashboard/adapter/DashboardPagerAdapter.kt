/*
 * Created by YSN Studio on 3/22/18 2:44 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 2:36 AM
 */

package com.ysn.dosist.views.ui.activities.dashboard.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ysn.dosist.views.ui.fragments.history.HistoryTransactionFragment
import com.ysn.dosist.views.ui.fragments.home.HomeFragment

/**
 * Created by yudisetiawan on 3/22/18.
 */
class DashboardPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val numItems = 2

    override fun getCount(): Int = numItems

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> HomeFragment.newInstance(page = 0, title = "Page 1")
        1 -> HistoryTransactionFragment.newInstance(page = 1, title = "Page 2")
        else -> {
            null
        }
    }

    override fun getPageTitle(position: Int): CharSequence = "Page $position"

}