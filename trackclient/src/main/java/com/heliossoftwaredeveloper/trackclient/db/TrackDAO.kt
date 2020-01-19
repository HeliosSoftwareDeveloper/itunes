/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Observable

/**
 * Interface for data access object of track table database
 *
 * @author Ruel N. Grajo on 01/19/2020.
 */

@Dao
interface TrackDao {
    @Query("SELECT * FROM TrackEntity")
    fun getAllTracks(): Observable<List<TrackEntity>>

    @Insert(onConflict = REPLACE)
    fun saveTracks(trackList: List<TrackEntity>)

    @Query("DELETE FROM TrackEntity")
    fun deleteTracks()
}