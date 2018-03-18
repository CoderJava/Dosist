/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/16/18 10:25 PM
 */

package com.ysn.dosist.di.component.home

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.home.HomeActivityModule
import com.ysn.dosist.views.ui.home.HomeActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/16/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(HomeActivityModule::class)])
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)

}