package com.ysn.dosist.views.ui.home

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.views.base.mvp.BasePresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/16/18.
 */
class HomePresenter @Inject constructor(private var dbManager: DbManager) : BasePresenter<HomeView>(), AnkoLogger {

    private val TAG = javaClass.simpleName

    fun onLoadData() {
        val resultBalanceCurrent = dbManager.queryBalanceCurrentBox()
        view?.loadData(resultBalanceCurrent = resultBalanceCurrent)
    }

}