/*
 * Created by YSN Studio on 3/30/18 7:10 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/30/18 7:09 PM
 */

package com.ysn.dosist.views.ui.activities.welcome

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ysn.dosist.R
import com.ysn.dosist.di.component.activities.welcome.DaggerWelcomeActivityComponent
import com.ysn.dosist.di.module.activities.welcome.WelcomeActivityModule
import com.ysn.dosist.views.base.BaseActivity
import com.ysn.dosist.views.ui.activities.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.text.DecimalFormat
import javax.inject.Inject

class WelcomeActivity : BaseActivity(), WelcomeView, View.OnClickListener {

    @Inject
    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initListeners()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerWelcomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .welcomeActivityModule(WelcomeActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.text_view_next_activity_welcome -> {
                val strAmount = edit_text_amount_activity_welcome.text.toString()
                        .trim()
                        .replace(",", "")
                        .replace(".", "")
                presenter.onSaveInitialMoney(strAmount = strAmount)
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    private fun initListeners() {
        text_view_next_activity_welcome.setOnClickListener(this)
        edit_text_amount_activity_welcome.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {
                edit_text_amount_activity_welcome.removeTextChangedListener(this)
                val strAmount = editable.toString().replace(",", "")
                        .replace(".", "")
                if (strAmount.isEmpty()) {
                    edit_text_amount_activity_welcome.setText("0")
                } else {
                    val amount = strAmount.toLong()
                    val formatAmount = DecimalFormat("#,###").format(amount)
                            .replace(",", ".")
                    edit_text_amount_activity_welcome.setText(formatAmount)
                    edit_text_amount_activity_welcome.setSelection(formatAmount.length)
                }
                edit_text_amount_activity_welcome.addTextChangedListener(this)
            }

            override fun beforeTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                /* nothing to do in here */
            }

            override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                /* nothing to do in here */
            }
        })
    }

    override fun getViewContext(): Context = this

    override fun saveInitialMoney() {
        startActivity(intentFor<DashboardActivity>().clearTask().newTask())
    }

    override fun saveInitialMoneyFailed(message: String?) {
        snackbar(find(android.R.id.content), message!!)
    }
}
