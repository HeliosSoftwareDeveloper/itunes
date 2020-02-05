/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient

import com.heliossoftwaredeveloper.applicationdatabase.entity.TrackEntity
import com.heliossoftwaredeveloper.trackclient.model.TrackDetailResult

/**
 * Mapper class for track client models
 *
 * @author Ruel N. Grajo on 02/05/2020.
 */

/**
 * Extension function to transform track api service model to entity database model
 *
 * @return List<TrackEntity> The transformed TrackEntity model
 */
fun List<TrackDetailResult>.serviceModelToTrackEntity() : List<TrackEntity> {
    return this.map {
        TrackEntity(
            id = 0,
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