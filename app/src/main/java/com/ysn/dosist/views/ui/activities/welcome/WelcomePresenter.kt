/*
 * Created by YSN Studio on 3/23/18 10:36 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:32 PM
 */

package com.ysn.dosist.views.ui.activities.welcome

import com.ysn.dosist.R
import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/23/18.
 */
class WelcomePresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<WelcomeView>() {

    fun onSaveInitialMoney(strAmount: String) {
        val context = view?.getViewContext()
        if (strAmount.isEmpty()) {
            view?.saveInitialMoneyFailed(context?.getString(R.string.amount_is_required))
            return
        }
        dbManager.putBalanceCurrentBox(BalanceCurrent(balance = strAmount.toLong()))
        view?.saveInitialMoney()
    }

}