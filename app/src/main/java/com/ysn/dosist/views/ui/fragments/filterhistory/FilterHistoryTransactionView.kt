/*
 * Created by YSN Studio on 3/30/18 6:43 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/29/18 11:52 PM
 */

package com.ysn.dosist.views.ui.fragments.filterhistory

import com.ysn.dosist.views.base.mvp.BaseView

interface FilterHistoryTransactionView : BaseView {

    fun loadData(indexYearSelected: String, indexMonthSelected: String)

    fun loadDataFailed()
}