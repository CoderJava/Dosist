/*
 * Created by YSN Studio on 3/22/18 3:33 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 3:32 AM
 */

package com.ysn.dosist.di.component.fragments.home

import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.fragments.home.HomeFragmentModule
import com.ysn.dosist.views.ui.fragments.home.HomeFragment
import dagger.Component

/**
 * Created by yudisetiawan on 3/22/18.
 */
@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(HomeFragmentModule::class)])
interface HomeFragmentComponent {

    fun inject(homeFragment: HomeFragment)

}