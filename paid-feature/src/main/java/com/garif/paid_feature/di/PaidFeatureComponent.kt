package com.garif.paid_feature.di

import com.garif.paid_feature.presentation.PaidFragment
import dagger.Subcomponent

@Subcomponent
interface PaidFeatureComponent {
    fun injectPaidFragment(paidFragment: PaidFragment)
}