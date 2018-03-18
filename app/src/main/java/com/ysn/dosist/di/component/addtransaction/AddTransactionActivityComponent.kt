/*
 * Created by YSN Studio on 3/18/18 5:04 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 5:02 PM
 */

package com.ysn.dosist.di.component.addtransaction

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.addtransaction.AddTransactionActivityModule
import com.ysn.dosist.views.ui.addtransaction.AddTransactionActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/18/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(AddTransactionActivityModule::class)])
interface AddTransactionActivityComponent {

    fun inject(addTransactionActivity: AddTransactionActivity)

}