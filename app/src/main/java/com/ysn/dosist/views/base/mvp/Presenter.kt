/*
 * Created by YSN Studio on 3/18/18 4:11 PM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/16/18 3:21 PM
 */

package com.ysn.dosist.views.base.mvp

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface Presenter<V: BaseView> {

    fun attachView(view: V)

    fun detachView()

}