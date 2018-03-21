/*
 * Created by YSN Studio on 3/22/18 3:33 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:32 AM
 */

package com.ysn.dosist.di.component.activities.addtransaction

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.activities.addtransaction.AddTransactionActivityModule
import com.ysn.dosist.views.ui.activities.addtransaction.AddTransactionActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/18/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(AddTransactionActivityModule::class)])
interface AddTransactionActivityComponent {

    fun inject(addTransactionActivity: AddTransactionActivity)

}