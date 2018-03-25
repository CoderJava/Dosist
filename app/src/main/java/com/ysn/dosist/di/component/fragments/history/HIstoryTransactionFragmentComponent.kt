/*
 * Created by YSN Studio on 3/25/18 11:57 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/25/18 11:56 AM
 */

package com.ysn.dosist.di.component.fragments.history

import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.fragments.history.HistoryTransactionFragmentModule
import com.ysn.dosist.views.ui.fragments.history.HistoryTransactionFragment
import dagger.Component

/**
 * Created by yudisetiawan on 3/25/18.
 */
@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(HistoryTransactionFragmentModule::class)])
interface HIstoryTransactionFragmentComponent {

    fun inject(historyTransactionFragment: HistoryTransactionFragment)

}