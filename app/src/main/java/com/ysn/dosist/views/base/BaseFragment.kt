/*
 * Created by YSN Studio on 3/22/18 2:04 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/22/18 2:04 AM
 */

package com.ysn.dosist.views.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 3/16/18.
 */
abstract class BaseFragment : Fragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    protected abstract fun onActivityInject()

    fun getAppComponent(): AppComponent = App.appComponent

    override fun setPresenter(presenter: BasePresenter<*>) {
        this.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        // TODO: do something in here if needed
    }

    override fun onStop() {
        super.onStop()
        // TODO: do something in here if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
        presenter = null
    }

}