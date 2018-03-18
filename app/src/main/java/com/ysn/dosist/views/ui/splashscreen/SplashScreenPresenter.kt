/*
 * Created by YSN Studio on 3/18/18 4:52 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:47 PM
 */

package com.ysn.dosist.views.ui.splashscreen

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