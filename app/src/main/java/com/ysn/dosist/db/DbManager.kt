package com.ysn.dosist.db

import android.app.Application
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.App
import io.objectbox.Box
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/17/18.
 */
class DbManager @Inject constructor(val application: Application) {

    private var balanceCurrentBox: Box<BalanceCurrent> = (application as App).boxStore.boxFor(BalanceCurrent::class.java)

    fun putBalanceCurrentBox(balanceCurrent: BalanceCurrent) {
        balanceCurrentBox.put(balanceCurrent)
    }

    fun queryBalanceCurrentBox(): BalanceCurrent {
        val countItem = balanceCurrentBox.count()
        return if (countItem == 0L) {
            putBalanceCurrentBox(BalanceCurrent())
            BalanceCurrent()
        } else {
            balanceCurrentBox.query().build().findFirst()!!
        }
    }

}