/*
 * Created by YSN Studio on 3/18/18 5:04 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:59 PM
 */

package com.ysn.dosist.views.ui.addtransaction

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.views.base.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/18/18.
 */
class AddTransactionPresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<AddTransactionView>() {
}