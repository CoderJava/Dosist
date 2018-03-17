package com.ysn.dosist.views.ui.home

import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface HomeView : BaseView {

    fun loadData(resultBalanceCurrent: BalanceCurrent)

}