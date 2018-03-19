/*
 * Created by YSN Studio on 3/20/18 2:20 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 12:47 AM
 */

package com.ysn.dosist.views.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.views.base.mvp.BasePresenter
import com.ysn.dosist.views.base.mvp.BaseView

/**
 * Created by yudisetiawan on 3/20/18.
 */
abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment(), BaseView {

    private var presenter: BasePresenter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onActivityInject()
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog?, style: Int) {
        super.setupDialog(dialog, style)
        // TODO: do something in here if needed
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