/*
 * Created by YSN Studio on 3/18/18 4:18 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:18 PM
 */

package com.ysn.dosist.di.component.splashscreen

import com.ysn.dosist.di.ActivityScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.splashscreen.SplashScreenActivityModule
import com.ysn.dosist.views.ui.splashscreen.SplashScreenActivity
import dagger.Component

/**
 * Created by yudisetiawan on 3/18/18.
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(SplashScreenActivityModule::class)])
interface SplashScreenActivityComponent {

    fun inject(splashScreenActivity: SplashScreenActivity)

}