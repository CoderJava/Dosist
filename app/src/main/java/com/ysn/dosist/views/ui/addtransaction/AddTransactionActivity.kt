/*
 * Created by YSN Studio on 3/18/18 10:36 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 10:31 PM
 */

package com.ysn.dosist.views.ui.addtransaction

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.DetailTransaction
import com.ysn.dosist.di.component.addtransaction.DaggerAddTransactionActivityComponent
import com.ysn.dosist.di.module.addtransaction.AddTransactionActivityModule
import com.ysn.dosist.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_transaction.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import javax.inject.Inject

class AddTransactionActivity : BaseActivity(), AddTransactionView, View.OnClickListener {

    @Inject
    lateinit var presenter: AddTransactionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        initListeners()
    }

    private fun initListeners() {
        text_view_income_activity_add_transaction.setOnClickListener(this)
        text_view_expense_activity_add_transaction.setOnClickListener(this)
        image_view_save_activity_add_transaction.setOnClickListener(this)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerAddTransactionActivityComponent.builder()
                .appComponent(getAppComponent())
                .addTransactionActivityModule(AddTransactionActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_income_activity_add_transaction -> incomeViewActive()
            R.id.text_view_expense_activity_add_transaction -> expenseViewActive()
            R.id.image_view_save_activity_add_transaction -> {
                val subjectTransaction = edit_text_subject_activity_add_transaction.text.toString()
                        .capitalize()
                        .trim()
                val descriptionTransaction = edit_text_description_activity_add_transaction.text.toString()
                        .capitalize()
                        .trim()
                val amountTransaction = edit_text_amount_add_transaction_activity.text.toString()
                        .replace(".", "")
                        .toLong()
                val category = text_view_category_activity_add_transaction.text.toString().toLowerCase()
                val drawableIncome = ContextCompat.getDrawable(this@AddTransactionActivity, R.drawable.background_rounded_income_active)
                val drawableIncomeSelected = text_view_income_activity_add_transaction.background
                val typeTransaction = if (drawableIncomeSelected == drawableIncome) "income" else "expense"
                val detailTransaction = DetailTransaction(
                        subject = subjectTransaction,
                        description = descriptionTransaction,
                        amount = amountTransaction,
                        idCategory = 1L,
                        type = typeTransaction
                )
                presenter.onSaveTransaction(detailTransaction = detailTransaction)
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    private fun expenseViewActive() {
        text_view_income_activity_add_transaction.background = null
        text_view_expense_activity_add_transaction.background = null
        text_view_income_activity_add_transaction.background = ContextCompat.getDrawable(this, R.drawable.background_rounded_income_inactive)
        text_view_expense_activity_add_transaction.background = ContextCompat.getDrawable(this, R.drawable.background_rounded_expense_active)
    }

    private fun incomeViewActive() {
        text_view_income_activity_add_transaction.background = null
        text_view_expense_activity_add_transaction.background = null
        text_view_income_activity_add_transaction.background = ContextCompat.getDrawable(this, R.drawable.background_rounded_income_active)
        text_view_expense_activity_add_transaction.background = ContextCompat.getDrawable(this, R.drawable.background_rounded_expense_inactive)
    }

    override fun getViewContext(): Context = this

    override fun saveTransactionFailed(message: String) {
        snackbar(view = findViewById(android.R.id.content), message = message)
    }

    override fun saveTransaction() {
        toast(getString(R.string.success_save_transaction))
        val mapData = HashMap<String, Any>()
        mapData["fromClass"] = javaClass.simpleName
        EventBus.getDefault().post(mapData)
        finish()
    }
}
