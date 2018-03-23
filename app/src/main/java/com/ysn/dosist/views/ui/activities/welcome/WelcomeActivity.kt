/*
 * Created by YSN Studio on 3/23/18 9:32 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/23/18 9:32 PM
 */

package com.ysn.dosist.views.ui.activities.welcome

import android.os.Bundle
import com.ysn.dosist.R
import com.ysn.dosist.di.component.activities.welcome.DaggerWelcomeActivityComponent
import com.ysn.dosist.di.module.activities.welcome.WelcomeActivityModule
import com.ysn.dosist.views.base.BaseActivity
import javax.inject.Inject

class WelcomeActivity : BaseActivity(), WelcomeView {

    @Inject
    lateinit var presenter: WelcomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerWelcomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .welcomeActivityModule(WelcomeActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

}
