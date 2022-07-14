package com.keecoding.f2pgames.presentation.di

import com.keecoding.f2pgames.presentation.adapter.GamesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AdapterModule {

    @Provides
    @Singleton
    fun provideGameAdapter() = GamesAdapter{}

}