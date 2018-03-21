/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.fragments.choosecategory

import com.ysn.dosist.db.entity.CategoryTransaction
import com.ysn.dosist.views.base.mvp.BaseView
import com.ysn.dosist.views.ui.fragments.choosecategory.adapter.AdapterChooseCategory

/**
 * Created by yudisetiawan on 3/20/18.
 */
interface ChooseCategoryView : BaseView {

    fun clickItem(chooseCategoryTransaction: CategoryTransaction)

    fun loadData(adapterChooseCategory: AdapterChooseCategory)
}