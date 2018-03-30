/*
 * Created by YSN Studio on 3/30/18 6:43 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 11:29 AM
 */

package com.ysn.dosist.views.ui.fragments.filterhistory

import com.ysn.dosist.views.base.mvp.BasePresenter
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

class FilterHistoryTransactionPresenter @Inject constructor() : BasePresenter<FilterHistoryTransactionView>(), AnkoLogger {

    fun onLoadData(arrYear: Array<String>, arrMonth: Array<String>, year: String, month: String) {
        var indexYearSelected = ""
        var indexMonthSelected = ""
        for (indexYear in arrYear.indices) {
            arrYear[indexYear].let {
                if (it.substring(2, it.length) == year) {
                    indexYearSelected = indexYear.toString()
                    return@let
                }
            }
            if (!indexYearSelected.isEmpty()) {
                break
            }
        }
        for (indexMonth in arrMonth.indices) {
            arrMonth[indexMonth].let {
                if (it == month) {
                    indexMonthSelected = indexMonth.toString()
                    return@let
                }
            }
            if (!indexMonthSelected.isEmpty()) {
                break
            }
        }

        if (indexYearSelected.isEmpty() || indexMonthSelected.isEmpty()) {
            view?.loadDataFailed()
        } else {
            view?.loadData(indexYearSelected = indexYearSelected, indexMonthSelected = indexMonthSelected)
        }
    }
}