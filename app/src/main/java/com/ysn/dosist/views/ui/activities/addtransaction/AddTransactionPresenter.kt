/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.activities.addtransaction

import com.ysn.dosist.R
import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.views.base.mvp.BasePresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/18/18.
 */
class AddTransactionPresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<AddTransactionView>(), AnkoLogger {

    fun onSaveTransaction(detailTransaction: DetailTransaction) {
        val categoryTransaction = dbManager.getAllCategoryTransactionBox()
        categoryTransaction.forEach {
            info { "idCategory: ${it.id} & nameCategory: ${it.name}" }
        }
        val context = view?.getViewContext()
        when {
            detailTransaction.subject.isEmpty() -> {
                view?.saveTransactionFailed(message = context!!.getString(R.string.subject_is_required))
                return
            }
            detailTransaction.description.isEmpty() -> {
                view?.saveTransactionFailed(message = context!!.getString(R.string.description_is_required))
                return
            }
            detailTransaction.amount == 0L -> {
                view?.saveTransactionFailed(message = context!!.getString(R.string.amount_is_required))
                return
            }
            detailTransaction.idCategory == 0L -> {
                view?.saveTransactionFailed(message = context!!.getString(R.string.category_is_required))
                return
            }
        }
        dbManager.putDetailTransactionBox(detailTransaction = detailTransaction)
        val balanceCurrent = dbManager.queryBalanceCurrentBox()
        when {
            detailTransaction.type.toLowerCase() == "income" -> {
                var incomeBalanceCurrent = balanceCurrent.income
                incomeBalanceCurrent += detailTransaction.amount
                balanceCurrent.income = incomeBalanceCurrent
                dbManager.putBalanceCurrentBox(balanceCurrent = balanceCurrent)
            }
            detailTransaction.type.toLowerCase() == "expense" -> {
                var expenseBalanceCurrent = dbManager.queryBalanceCurrentBox().expense
                expenseBalanceCurrent += detailTransaction.amount
                balanceCurrent.expense = expenseBalanceCurrent
                dbManager.putBalanceCurrentBox(balanceCurrent = balanceCurrent)
            }
        }
        view?.saveTransaction()
    }
}