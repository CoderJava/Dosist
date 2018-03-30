/*
 * Created by YSN Studio on 3/30/18 6:43 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 11:08 AM
 */

package com.ysn.dosist.views.ui.fragments.filterhistory

import android.app.Dialog
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.ysn.dosist.R
import com.ysn.dosist.di.component.fragments.filterhistory.DaggerFilterHistoryTransactionBottomSheetDialogFragmentComponent
import com.ysn.dosist.di.module.fragments.filterhistory.FilterHistoryTransactionBottomSheetDialogFragmentModule
import com.ysn.dosist.views.base.BaseBottomSheetDialogFragment
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class FilterHistoryTransactionBottomSheetDialogFragment : BaseBottomSheetDialogFragment(), FilterHistoryTransactionView, View.OnClickListener, AnkoLogger {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: FilterHistoryTransactionPresenter

    private lateinit var numberPickerMonthFilterHistoryTransaction: com.shawnlin.numberpicker.NumberPicker
    private lateinit var numberPickerYearFilterHistoryTransaction: com.shawnlin.numberpicker.NumberPicker
    private lateinit var buttonSaveFilterHistoryTransaction: Button
    private lateinit var buttonCancelFilterHistoryTransaction: Button

    private lateinit var arrMonth: Array<String>
    private lateinit var arrYear: Array<String>
    private lateinit var indexMonthSelected: String
    private lateinit var indexYearSelected: String

    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        val view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog_fragment_filter_history_transaction, null)
        dialog?.setContentView(view)
        val layoutParams = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = layoutParams.behavior
        if (behavior != null) {
            (behavior as BottomSheetBehavior).isHideable = false
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            view.find<RelativeLayout>(R.id.relative_layout_container_bottom_sheet_dialog_fragment_filter_history_transaction).let {
                it.post {
                    behavior.peekHeight = it.height
                }
            }
        }
        initViews(view = view)
        initListeners()
        doLoadData()
    }

    private fun initListeners() {
        numberPickerMonthFilterHistoryTransaction.setOnValueChangedListener { _, _, newVal ->
            indexMonthSelected = newVal.toString()
        }
        numberPickerYearFilterHistoryTransaction.setOnValueChangedListener { _, _, newVal ->
            indexYearSelected = newVal.toString()
        }
        buttonSaveFilterHistoryTransaction.setOnClickListener(this)
        buttonCancelFilterHistoryTransaction.setOnClickListener(this)
    }

    private fun initViews(view: View) {
        numberPickerMonthFilterHistoryTransaction = view.find(R.id.number_picker_month_bottom_sheet_dialog_fragment_filter_history_transaction)
        arrMonth = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        numberPickerMonthFilterHistoryTransaction.minValue = 0
        numberPickerMonthFilterHistoryTransaction.maxValue = 11
        numberPickerMonthFilterHistoryTransaction.setFormatter { arrMonth[it] }
        numberPickerMonthFilterHistoryTransaction.wrapSelectorWheel = false

        numberPickerYearFilterHistoryTransaction = view.find(R.id.number_picker_year_bottom_sheet_dialog_fragment_filter_history_transaction)
        val year = SimpleDateFormat("yyyy", Locale.US).format(Date()).toInt()
        arrYear = Array(10, { a -> (year - a).toString() })
        numberPickerYearFilterHistoryTransaction.minValue = 0
        numberPickerYearFilterHistoryTransaction.maxValue = arrYear.size - 1
        numberPickerYearFilterHistoryTransaction.setFormatter { arrYear[it] }
        numberPickerYearFilterHistoryTransaction.wrapSelectorWheel = false

        buttonSaveFilterHistoryTransaction = view.find(R.id.button_save_bottom_sheet_dialog_fragment_filter_history_transaction)
        buttonCancelFilterHistoryTransaction = view.find(R.id.button_cancel_bottom_sheet_dialog_fragment_filter_history_transaction)
    }

    private fun doLoadData() {
        val bundle = arguments
        val month = bundle!!.getString("month")
        val year = bundle.getString("year")
        presenter.onLoadData(arrYear = arrYear, arrMonth = arrMonth, year = year, month = month)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerFilterHistoryTransactionBottomSheetDialogFragmentComponent.builder()
                .appComponent(getAppComponent())
                .filterHistoryTransactionBottomSheetDialogFragmentModule(FilterHistoryTransactionBottomSheetDialogFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun loadData(indexYearSelected: String, indexMonthSelected: String) {
        this.indexMonthSelected = indexMonthSelected
        this.indexYearSelected = indexYearSelected
        numberPickerMonthFilterHistoryTransaction.value = indexMonthSelected.toInt()
        numberPickerYearFilterHistoryTransaction.value = indexYearSelected.toInt()
    }

    override fun loadDataFailed() {
        /* nothing to do in here */
    }

    override fun onClick(view: View) = when (view.id) {
        R.id.button_save_bottom_sheet_dialog_fragment_filter_history_transaction -> {
            val mapData = HashMap<String, Any>()
            mapData["fromClass"] = TAG
            mapData["monthYearFilter"] = "${arrMonth[indexMonthSelected.toInt()]} ${arrYear[indexYearSelected.toInt()]}"
            EventBus.getDefault().post(mapData)
            dismiss()
        }
        R.id.button_cancel_bottom_sheet_dialog_fragment_filter_history_transaction -> dismiss()
        else -> {
            /* nothing to do in here */
        }
    }
}