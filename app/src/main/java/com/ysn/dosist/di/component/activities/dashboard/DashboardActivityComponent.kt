/*
 * Created by YSN Studio on 3/22/18 3:33 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:32 AM
 */

package com.ysn.dosist.di.component.activities.dashboard

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.activities.dashboard.DashboardActivityModule
import com.ysn.dosist.views.ui.activities.dashboard.DashboardActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/16/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(DashboardActivityModule::class)])
interface DashboardActivityComponent {

    fun inject(homeActivity: DashboardActivity)

}