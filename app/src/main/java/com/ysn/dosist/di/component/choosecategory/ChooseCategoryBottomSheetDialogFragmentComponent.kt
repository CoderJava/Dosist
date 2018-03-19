/*
 * Created by YSN Studio on 3/20/18 2:22 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 3/20/18 12:47 AM
 */

package com.ysn.dosist.di.component.choosecategory

import com.ysn.dosist.di.FragmentScope
import com.ysn.dosist.di.component.AppComponent
import com.ysn.dosist.di.module.choosecategory.ChooseCategoryBottomSheetDialogFragmentModule
import com.ysn.dosist.views.ui.choosecategory.ChooseCategoryBottomSheetDialogFragment
import dagger.Component

/**
 * Created by yudisetiawan on 3/20/18.
 */
@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(ChooseCategoryBottomSheetDialogFragmentModule::class)])
interface ChooseCategoryBottomSheetDialogFragmentComponent {

    fun inject(chooseCategoryBottomSheetDialogFragment: ChooseCategoryBottomSheetDialogFragment)

}