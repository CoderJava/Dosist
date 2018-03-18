/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 11:31 AM
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
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/17/18.
 */
class DbManager @Inject constructor(val application: Application) {

    private var balanceCurrentBox: Box<BalanceCurrent> = (application as App).boxStore.boxFor(BalanceCurrent::class.java)
    private var categoryTransaction: Box<CategoryTransaction> = (application as App).boxStore.boxFor(CategoryTransaction::class.java)
    private var detailTransaction: Box<DetailTransaction> = (application as App).boxStore.boxFor(DetailTransaction::class.java)

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
     * Get Category DetailTransaction by ID Category
     * @param idCategory - Value ID Category
     * @return Result value Category DetailTransaction
     */
    fun getCategoryTransactionById(idCategory: Long): CategoryTransaction {
        return categoryTransaction.get(idCategory)
    }

    /**
     * Get all DetailTransaction
     * @return Result list DetailTransaction
     */
    fun queryGetAllTransaction(): Query<DetailTransaction> {
        return detailTransaction.query().order(DetailTransaction_.id).build()
    }

}