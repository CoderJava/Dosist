/*
 * Created by YSN Studio on 3/23/18 11:02 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 10:57 PM
 */

package com.ysn.dosist.views.ui.activities.splashscreen

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.model.SharedPreferencesDosist
import com.ysn.dosist.views.base.mvp.BasePresenter
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/18/18.
 */
class SplashScreenPresenter @Inject constructor(private val dbManager: DbManager, private val sharedPreferencesDosist: SharedPreferencesDosist) : BasePresenter<SplashScreenView>() {

    fun onCheckSetup() {
        if (sharedPreferencesDosist.getDataBoolean(SharedPreferencesDosist.ALREADY_SETUP)) {
            view?.setupCategoryAlready()
        } else {
            dbManager.prepareDataCategoryTransactionBox()
            view?.setupCategoryTransactionData()
        }
    }
}