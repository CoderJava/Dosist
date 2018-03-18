/*
 * Created by YSN Studio on 3/18/18 10:36 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 9:48 PM
 */

package com.ysn.dosist.views.ui.addtransaction

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