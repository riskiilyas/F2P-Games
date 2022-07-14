package com.keecoding.f2pgames.presentation.di

import android.app.Application
import androidx.room.Room
import com.keecoding.f2pgames.data.db.AppDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDB {
        return Room.databaseBuilder(app, AppDB::class.java, "APP_DB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideGameDao(appDB: AppDB) = appDB.gameDao()

    @Provides
    @Singleton
    fun provideGameDetailDao(appDB: AppDB) = appDB.gameDetailDao()
}