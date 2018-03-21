/*
 * Created by YSN Studio on 3/22/18 2:06 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 2:06 AM
 */

package com.ysn.dosist.views.ui.fragments.home


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ysn.dosist.R
import com.ysn.dosist.di.component.home.DaggerHomeFragmentComponent
import com.ysn.dosist.di.module.home.HomeFragmentModule
import com.ysn.dosist.views.base.BaseFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homeFragmentModule(HomeFragmentModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
