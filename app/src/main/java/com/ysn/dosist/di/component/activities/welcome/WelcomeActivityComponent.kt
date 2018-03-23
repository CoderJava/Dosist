/*
 * Created by YSN Studio on 3/23/18 9:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 9:30 PM
 */

package com.ysn.dosist.di.component.activities.welcome

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.activities.welcome.WelcomeActivityModule
import com.ysn.dosist.views.ui.activities.welcome.WelcomeActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/23/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(WelcomeActivityModule::class)])
interface WelcomeActivityComponent {

    fun inject(welcomeActivity: WelcomeActivity)

}