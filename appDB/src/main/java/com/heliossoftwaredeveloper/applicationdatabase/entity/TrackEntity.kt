/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.applicationdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for Track database table
 *
 * @author Ruel N. Grajo on 01/19/2020.
 */

@Entity(tableName = "TrackEntity")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "kind") var kind: String?,
    @ColumnInfo(name = "trackId") var trackId: Long?,
    @ColumnInfo(name = "artistName") var artistName: String?,
    @ColumnInfo(name = "collectionName") var collectionName: String?,
    @ColumnInfo(name = "trackName") var trackName: String?,
    @ColumnInfo(name = "artworkUrl") var artworkUrl: String?,
    @ColumnInfo(name = "collectionPrice") var collectionPrice: Double?,
    @ColumnInfo(name = "trackPrice") var trackPrice: Double?,
    @ColumnInfo(name = "trackRentalPrice") var trackRentalPrice: Double?,
    @ColumnInfo(name = "collectionHdPrice") var collectionHdPrice: Double?,
    @ColumnInfo(name = "trackHdPrice") var trackHdPrice: Double?,
    @ColumnInfo(name = "trackHdRentalPrice") var trackHdRentalPrice: Double?,
    @ColumnInfo(name = "releaseDate") var releaseDate: String?,
    @ColumnInfo(name = "collectionExplicitness") var collectionExplicitness: String?,
    @ColumnInfo(name = "trackExplicitness") var trackExplicitness: String?,
    @ColumnInfo(name = "trackCount") var trackCount: Int?,
    @ColumnInfo(name = "trackNumber") var trackNumber: Int?,
    @ColumnInfo(name = "trackTimeMillis") var trackTimeMillis: Long?,
    @ColumnInfo(name = "country") var country: String?,
    @ColumnInfo(name = "currency") var currency: String?,
    @ColumnInfo(name = "primaryGenreName") var primaryGenreName: String?,
    @ColumnInfo(name = "contentAdvisoryRating") var contentAdvisoryRating: String?,
    @ColumnInfo(name = "shortDescription") var shortDescription: String?,
    @ColumnInfo(name = "longDescription") var longDescription: String?
)