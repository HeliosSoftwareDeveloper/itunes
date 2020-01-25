/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.applicationdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.heliossoftwaredeveloper.applicationdatabase.dao.TrackDao
import com.heliossoftwaredeveloper.applicationdatabase.entity.TrackEntity

/**
 * Database class for the application
 *
 * @author Ruel N. Grajo on 01/25/2020.
 */

@Database(entities = [TrackEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao

    companion object {
        const val DB_NAME = "DBTrack"
    }
}