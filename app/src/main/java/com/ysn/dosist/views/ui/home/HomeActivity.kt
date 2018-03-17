package com.ysn.dosist.views.ui.home

import android.os.Bundle
import com.ysn.dosist.R
import com.ysn.dosist.db.entity.BalanceCurrent
import com.ysn.dosist.di.component.home.DaggerHomeActivityComponent
import com.ysn.dosist.di.module.home.HomeActivityModule
import com.ysn.dosist.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import java.text.DecimalFormat
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {

    private val TAG = javaClass.simpleName

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

    override fun onResume() {
        super.onResume()
        presenter.onLoadData()
    }

    override fun onError() {
        // TODO: do something in here if needed
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder()
                .appComponent(getAppComponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)
        presenter.attachView(this)
    }

    override fun loadData(resultBalanceCurrent: BalanceCurrent) {
        text_view_overview_activity_main.text = resultBalanceCurrent.balance.toString()
        val decimalFormat = DecimalFormat("#,###")
        val incomeFormat = decimalFormat.format(resultBalanceCurrent.income)
        val expenseFormat = decimalFormat.format(resultBalanceCurrent.expense)
        text_view_income_activity_main.text = getString(R.string.income_format, incomeFormat)
        text_view_expense_activity_main.text = getString(R.string.expense_format, expenseFormat)
    }

}
