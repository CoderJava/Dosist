package com.ysn.dosist.views.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.component.DaggerAppComponent
import com.ysn.dosist.di.module.AppModule

/**
 * Created by yudisetiawan on 3/16/18.
 */
class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        Fresco.initialize(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}