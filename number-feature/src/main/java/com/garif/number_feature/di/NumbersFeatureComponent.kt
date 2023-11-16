package com.garif.number_feature.di

import com.garif.network.NetworkModule
import com.garif.number_feature.presentation.NumbersFragment
import dagger.Component
import dagger.Subcomponent

@Subcomponent(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        NumbersModule::class,
        RepoModule::class]
)
interface NumbersFeatureComponent {
    fun injectNumbersFragment(numbersFragment: NumbersFragment)
}