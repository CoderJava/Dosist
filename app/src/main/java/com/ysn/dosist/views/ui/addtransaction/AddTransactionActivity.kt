/*
 * Created by YSN Studio on 3/18/18 5:04 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 5:04 PM
 */

package com.ysn.dosist.views.ui.addtransaction

import android.os.Bundle
import com.ysn.dosist.R
import com.ysn.dosist.di.component.addtransaction.DaggerAddTransactionActivityComponent
import com.ysn.dosist.di.module.addtransaction.AddTransactionActivityModule
import com.ysn.dosist.views.base.BaseActivity
import javax.inject.Inject

class AddTransactionActivity : BaseActivity(), AddTransactionView {

    @Inject
    private lateinit var presenter: AddTransactionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
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

}
