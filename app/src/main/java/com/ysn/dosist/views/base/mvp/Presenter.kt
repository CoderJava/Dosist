package com.ysn.dosist.views.base.mvp

/**
 * Created by yudisetiawan on 3/16/18.
 */
interface Presenter<V: BaseView> {

    fun attachView(view: V)

    fun detachView()

}