/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.activities.addtransaction

import android.content.Context
import com.ysn.dosist.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 3/18/18.
 */
interface AddTransactionView : BaseView {

    fun getViewContext(): Context

    fun saveTransactionFailed(message: String)

    fun saveTransaction()
}