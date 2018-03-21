/*
 * Created by YSN Studio on 3/22/18 1:42 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 1:41 AM
 */

package com.ysn.dosist.views.ui.fragments.choosecategory

import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.CategoryTransaction
import com.ysn.dosist.di.component.choosecategory.DaggerChooseCategoryBottomSheetDialogFragmentComponent
import com.ysn.dosist.di.module.choosecategory.ChooseCategoryBottomSheetDialogFragmentModule
import com.ysn.dosist.views.base.BaseBottomSheetDialogFragment
import com.ysn.dosist.views.ui.fragments.choosecategory.adapter.AdapterChooseCategory
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find
import javax.inject.Inject

/**
 * Created by yudisetiawan on 3/20/18.
 */
class ChooseCategoryBottomSheetDialogFragment : BaseBottomSheetDialogFragment(), ChooseCategoryView {

    @Inject
    lateinit var presenter: ChooseCategoryPresenter

    private lateinit var recyclerViewChooseCategory: RecyclerView

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog_fragment_choose_category, null)
        dialog?.setContentView(view)
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null) {
            (behavior as BottomSheetBehavior).isHideable = true
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            view.find<RelativeLayout>(R.id.relative_layout_container_bottom_sheet_dialog_fragment_choose_category).let {
                it.post {
                    behavior.peekHeight = it.height
                }
            }
        }
        initViews(view = view)
        doLoadData()
    }

    private fun initViews(view: View) {
        view.find<TextView>(R.id.text_view_title_toolbar_normal).text = getString(R.string.choose_category)
        recyclerViewChooseCategory = view.find(R.id.recycler_view_category_transaction_bottom_sheet_dialog_fragment_choose_category)
    }

    private fun doLoadData() {
        presenter.onLoadData()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerChooseCategoryBottomSheetDialogFragmentComponent.builder()
                .appComponent(getAppComponent())
                .chooseCategoryBottomSheetDialogFragmentModule(ChooseCategoryBottomSheetDialogFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun loadData(adapterChooseCategory: AdapterChooseCategory) {
        recyclerViewChooseCategory.layoutManager = LinearLayoutManager(context)
        recyclerViewChooseCategory.adapter = adapterChooseCategory
    }

    override fun clickItem(chooseCategoryTransaction: CategoryTransaction) {
        val mapDataReturn = HashMap<String, Any>()
        mapDataReturn["fromClass"] = javaClass.simpleName
        mapDataReturn["chooseCategory"] = chooseCategoryTransaction
        EventBus.getDefault().post(mapDataReturn)
        dismiss()
    }
}