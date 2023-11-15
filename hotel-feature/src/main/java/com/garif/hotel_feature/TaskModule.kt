package com.garif.hotel_feature

import com.garif.network.Api
import dagger.Module
import dagger.Provides

@Module
class TaskModule {
    @Provides
    fun provideTaskRepository(taskApi: Api): TaskRepository {
        return TaskRepository(taskApi)
    }
}