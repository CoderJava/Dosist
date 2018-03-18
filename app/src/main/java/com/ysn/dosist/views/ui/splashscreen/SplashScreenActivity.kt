/*
 * Created by YSN Studio on 3/18/18 4:52 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/18/18 4:42 PM
 */

package com.ysn.dosist.views.ui.splashscreen

import android.os.Bundle
import com.ysn.dosist.R
import com.ysn.dosist.di.component.splashscreen.DaggerSplashScreenActivityComponent
import com.ysn.dosist.di.module.splashscreen.SplashScreenActivityModule
import com.ysn.dosist.views.base.BaseActivity
import com.ysn.dosist.views.ui.home.HomeActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashScreenActivity : BaseActivity(), SplashScreenView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: SplashScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onResume() {
        super.onResume()
        presenter.onSetupCategoryTransactionData()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerSplashScreenActivityComponent.builder()
                .appComponent(getAppComponent())
                .splashScreenActivityModule(SplashScreenActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun setupCategoryTransactionData() {
        Observable.just(true)
                .delay(1000 * 3, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    startActivity(intentFor<HomeActivity>().clearTask().newTask())
                }
    }

}
