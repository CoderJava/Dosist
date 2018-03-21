/*
 * Created by YSN Studio on 3/22/18 3:09 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:08 AM
 */

package com.ysn.dosist.views.ui.activities.dashboard

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.views.base.mvp.BasePresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/16/18.
 */
class DashboardPresenter @Inject constructor(private var dbManager: DbManager) : BasePresenter<DashboardView>(), AnkoLogger {

}