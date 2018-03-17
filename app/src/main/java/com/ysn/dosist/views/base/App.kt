package com.ysn.dosist.views.base

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.ysn.dosist.db.entity.MyObjectBox
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.component.DaggerAppComponent
import com.ysn.dosist.di.module.AppModule
import io.objectbox.BoxStore

/**
 * Created by yudisetiawan on 3/16/18.
 */
class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    lateinit var boxStore: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initObjectBox()
        Fresco.initialize(this)
    }

    private fun initObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(this).build()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}