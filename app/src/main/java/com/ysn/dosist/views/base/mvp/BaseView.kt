package com.ysn.dosist.views.base.mvp

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface BaseView {

    fun onError()

    fun setPresenter(presenter: BasePresenter<*>)
}