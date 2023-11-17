package com.garif.number_feature.di.module

import com.garif.number_feature.data.NumbersRepoImpl
import com.garif.number_feature.domain.repo.NumbersRepo
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun numbersRepo(
        impl: NumbersRepoImpl
    ): NumbersRepo
}