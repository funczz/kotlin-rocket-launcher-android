package com.github.funczz.kotlin.rocket_launcher.android.di

import com.github.funczz.kotlin.rocket_launcher.android.UiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProvideModule {

    //@Singleton
    @Provides
    fun provideUiState(): UiState = UiState()

}