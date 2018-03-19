/*
 * Created by YSN Studio on 3/20/18 2:22 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 1:20 AM
 */

package com.ysn.dosist.views.ui.choosecategory

import com.ysn.dosist.db.DbManager
import com.ysn.dosist.db.entity.CategoryTransaction
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.ui.choosecategory.adapter.AdapterChooseCategory
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/20/18.
 */
class ChooseCategoryPresenter @Inject constructor(private val dbManager: DbManager) : BasePresenter<ChooseCategoryView>() {

    fun onLoadData() {
        val categories = dbManager.getAllCategoryTransactionBox()
        val adapterChooseCategory = AdapterChooseCategory(
                categories = categories,
                listenerAdapterChooseCategory = object : AdapterChooseCategory.ListenerAdapterChooseCategory {
                    override fun onClickItem(chooseCategoryTransaction: CategoryTransaction) {
                        view?.clickItem(chooseCategoryTransaction = chooseCategoryTransaction)
                    }
                })
        view?.loadData(adapterChooseCategory = adapterChooseCategory)
    }
}