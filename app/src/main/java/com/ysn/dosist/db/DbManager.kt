/*
 * Created by YSN Studio on 3/18/18 10:36 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 10:17 PM
 */

package com.ysn.dosist.db

import android.app.Application
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.db.entity.CategoryTransaction
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.db.entity.DetailTransaction_
import com.ysn.dosist.views.base.App
import io.objectbox.Box
import io.objectbox.query.Query
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/17/18.
 */
class DbManager @Inject constructor(val application: Application) : AnkoLogger {

    private var balanceCurrentBox: Box<BalanceCurrent> = (application as App).boxStore.boxFor(BalanceCurrent::class.java)
    private var categoryTransactionBox: Box<CategoryTransaction> = (application as App).boxStore.boxFor(CategoryTransaction::class.java)
    private var detailTransactionBox: Box<DetailTransaction> = (application as App).boxStore.boxFor(DetailTransaction::class.java)

    /**
     * Put Balance Current Box
     * @param balanceCurrent - Value balance current
     */
    fun putBalanceCurrentBox(balanceCurrent: BalanceCurrent) {
        balanceCurrentBox.put(balanceCurrent)
    }

    /**
     * Query Balance Current Box
     */
    fun queryBalanceCurrentBox(): BalanceCurrent {
        val countItem = balanceCurrentBox.count()
        return if (countItem == 0L) {
            putBalanceCurrentBox(BalanceCurrent())
            BalanceCurrent()
        } else {
            balanceCurrentBox.query().build().findFirst()!!
        }
    }

    /**
     * Put Category Transaction Box
     */
    fun prepareDataCategoryTransactionBox() {
        if (categoryTransactionBox.count() != 7L) {
            categoryTransactionBox.put(CategoryTransaction(name = "Food Drink"))
            categoryTransactionBox.put(CategoryTransaction(name = "Shopping"))
            categoryTransactionBox.put(CategoryTransaction(name = "Salary"))
            categoryTransactionBox.put(CategoryTransaction(name = "Lifestyle"))
            categoryTransactionBox.put(CategoryTransaction(name = "Health"))
            categoryTransactionBox.put(CategoryTransaction(name = "Travelling"))
            categoryTransactionBox.put(CategoryTransaction(name = "Other"))
        }
    }

    /**
     * Get all Category Transaction
     * @return Result Category Transactions
     */
    fun getAllCategoryTransactionBox(): List<CategoryTransaction> = categoryTransactionBox.all

    /**
     * Get Category Transaction by ID Category
     * @param idCategory - Value ID Category
     * @return Result value Category DetailTransaction
     */
    fun getCategoryTransactionByIdBox(idCategory: Long): CategoryTransaction {
        return categoryTransactionBox.get(idCategory)
    }

    /**
     * Get all Detail Transaction
     * @return Result list Detail Transaction
     */
    fun queryGetAllTransactionBox(): Query<DetailTransaction> {
        return detailTransactionBox.query().order(DetailTransaction_.id).build()
    }

    /**
     * Put Detail Transaction Box
     * @param detailTransaction - Value Detail Transaction
     */
    fun putDetailTransactionBox(detailTransaction: DetailTransaction) {
        detailTransactionBox.put(detailTransaction)
    }


}