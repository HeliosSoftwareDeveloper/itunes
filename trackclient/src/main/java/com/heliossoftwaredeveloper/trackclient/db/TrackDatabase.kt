/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.storefinder.Store.Storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heliossoftwaredeveloper.trackclient.db.TrackDao
import com.heliossoftwaredeveloper.trackclient.db.TrackEntity

/**
 * Database class for Track
 *
 * @author Ruel N. Grajo on 01/19/2020.
 */

@Database(entities = [TrackEntity::class], version = 1)
abstract class TrackDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao

    companion object {
        @Volatile
        var INSTANCE: TrackDatabase? = null
        const val DB_NAME = "DBTrack"

        fun getAppDataBase(context: Context): TrackDatabase? {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}