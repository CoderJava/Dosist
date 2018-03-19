/*
 * Created by YSN Studio on 3/20/18 2:22 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 1:20 AM
 */

package com.ysn.dosist.views.ui.choosecategory

import com.ysn.dosist.db.entity.CategoryTransaction
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.choosecategory.adapter.AdapterChooseCategory

/**
 * Created by yudisetiawan on 3/20/18.
 */
interface ChooseCategoryView : BaseView {

    fun clickItem(chooseCategoryTransaction: CategoryTransaction)

    fun loadData(adapterChooseCategory: AdapterChooseCategory)
}