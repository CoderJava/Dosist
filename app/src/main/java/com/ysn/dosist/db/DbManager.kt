/*
 * Created by YSN Studio on 3/25/18 1:31 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 1:31 PM
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
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*
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
     * Get all Detail Transaction by Month and Year
     * @param month - Value month transaction filter
     * @param year - Value year transaction filter
     */
    fun queryGetAllTransactionByMonthAndYear(month: String, year: String): Query<DetailTransaction> {
        return detailTransactionBox.query().order(DetailTransaction_.id).filter {
            val strDate = SimpleDateFormat("MMM yy", Locale.US).format(DateTime(it.timestamp).toDate())
                    .split(" ")
            val monthTransaction = strDate[0]
            val yearTransaction = strDate[1]
            month == monthTransaction && year == yearTransaction
        }.build()
    }

    /**
     * Put Detail Transaction Box
     * @param detailTransaction - Value Detail Transaction
     */
    fun putDetailTransactionBox(detailTransaction: DetailTransaction) {
        detailTransactionBox.put(detailTransaction)
    }


}