/*
 * Created by YSN Studio on 3/23/18 10:36 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:34 PM
 */

package com.ysn.dosist.views.ui.activities.welcome

import android.content.Context
import com.ysn.dosist.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 3/23/18.
 */
interface WelcomeView : BaseView {

    fun getViewContext(): Context

    fun saveInitialMoney()

    fun saveInitialMoneyFailed(message: String?)

}