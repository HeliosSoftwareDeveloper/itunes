/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.util

import com.heliossoftwaredeveloper.applicationdatabase.entity.TrackEntity
import com.heliossoftwaredeveloper.trackclient.model.TrackDetailResult
import com.heliossoftwaredeveloper.trackui.model.TrackItem

/**
 * Mapper class for track models
 *
 * @author Ruel N. Grajo on 01/21/2020.
 */

/**
 * Extension function to transform track entity database model to display model
 *
 * @return List<TrackItem> The transformed display model
 */
fun List<TrackEntity>.entityModelToTrackItem() : List<TrackItem> {
    return this.map {
        TrackItem(
            kind = it.kind,
            trackId = it.trackId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            trackName = it.trackName,
            artworkUrl = it.artworkUrl,
            collectionPrice = it.collectionPrice,
            trackPrice = it.trackPrice,
            trackRentalPrice = it.trackRentalPrice,
            collectionHdPrice = it.collectionHdPrice,
            trackHdPrice = it.trackHdPrice,
            trackHdRentalPrice = it.trackHdRentalPrice,
            releaseDate = it.releaseDate,
            collectionExplicitness = it.collectionExplicitness,
            trackExplicitness = it.trackExplicitness,
            trackCount = it.trackCount,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            contentAdvisoryRating = it.contentAdvisoryRating,
            shortDescription = it.shortDescription,
            longDescription = it.longDescription
        )
    }
}

/**
 * Extension function to transform track api service model to display model
 *
 * @return List<TrackItem> The transformed display model
 */
fun List<TrackDetailResult>.serviceModelToTrackItem() : List<TrackItem> {
    return this.map {
        TrackItem(
            kind = it.kind,
            trackId = it.trackId,
            artistName = it.artistName,
            collectionName = it.collectionName,
            trackName = it.trackName,
            artworkUrl = it.artworkUrl100,
            collectionPrice = it.collectionPrice,
            trackPrice = it.trackPrice,
            trackRentalPrice = it.trackRentalPrice,
            collectionHdPrice = it.collectionHdPrice,
            trackHdPrice = it.trackHdPrice,
            trackHdRentalPrice = it.trackHdRentalPrice,
            releaseDate = it.releaseDate,
            collectionExplicitness = it.collectionExplicitness,
            trackExplicitness = it.trackExplicitness,
            trackCount = it.trackCount,
            trackNumber = it.trackNumber,
            trackTimeMillis = it.trackTimeMillis,
            country = it.country,
            currency = it.currency,
            primaryGenreName = it.primaryGenreName,
            contentAdvisoryRating = it.contentAdvisoryRating,
            shortDescription = it.shortDescription,
            longDescription = it.longDescription
        )
    }
}