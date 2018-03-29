/*
 * Created by YSN Studio on 3/29/18 9:00 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/29/18 8:55 PM
 */

package com.ysn.dosist.views.ui.activities.about

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ysn.dosist.BuildConfig
import com.ysn.dosist.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        doLoadData()
    }

    private fun doLoadData() {
        val versionName = BuildConfig.VERSION_NAME
        text_view_version_name_activity_about.text = versionName
    }
}
