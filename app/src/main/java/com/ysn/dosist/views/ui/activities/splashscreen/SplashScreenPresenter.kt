/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.activities.splashscreen

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.views.base.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/18/18.
 */
class SplashScreenPresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<SplashScreenView>() {

    fun onSetupCategoryTransactionData() {
        dbManager.prepareDataCategoryTransactionBox()
        view?.setupCategoryTransactionData()
    }
}