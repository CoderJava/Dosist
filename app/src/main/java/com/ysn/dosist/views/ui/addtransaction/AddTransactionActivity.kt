/*
 * Created by YSN Studio on 3/18/18 7:51 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 7:45 PM
 */

package com.ysn.dosist.views.ui.addtransaction

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.di.component.addtransaction.DaggerAddTransactionActivityComponent
import com.ysn.dosist.di.module.addtransaction.AddTransactionActivityModule
import com.ysn.dosist.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_add_transaction.*
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
            R.id.text_view_income_activity_add_transaction -> {
                incomeViewActive()
            }
            R.id.text_view_expense_activity_add_transaction -> {
                expenseViewActive()
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

}
