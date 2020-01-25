/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.applicationdatabase.di

import android.app.Application
import dagger.Module
import dagger.Provides
import androidx.room.Room
import com.heliossoftwaredeveloper.applicationdatabase.AppDatabase
import com.heliossoftwaredeveloper.applicationdatabase.dao.TrackDao
import javax.inject.Singleton

/**
 * Dependency Module for Application Database
 *
 * @author Ruel N. Grajo on 01/25/2020.
 */

@Module
class DatabaseModule(private val mApplication: Application)  {

    private var appDatabase: AppDatabase

    init {
        synchronized(this) {
            val instance = Room.databaseBuilder(mApplication, AppDatabase::class.java, AppDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
            appDatabase = instance
            instance
        }
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return appDatabase
    }

    @Singleton
    @Provides
    fun providesTrackDao(appDatabase: AppDatabase): TrackDao {
        return appDatabase.trackDao()
    }
}