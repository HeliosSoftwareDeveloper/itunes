/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.repository

import com.heliossoftwaredeveloper.storefinder.Store.Storage.TrackDatabase
import com.heliossoftwaredeveloper.trackclient.TrackApiClient
import com.heliossoftwaredeveloper.trackclient.db.TrackEntity
import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Repository class for Track related stuff
 *
 * @author Ruel N. Grajo on 01/16/2020.
 */

interface TrackRepository {
    /**
     * Function to execute search track by term, country, & media
     *
     * @param term the keyword of track that the user is looking for.
     * @param country the two-letter country code for the store. Example: US, AU, PH. The default is US.
     * @param media the media type of the track. Example: movie, podcast, music, musicVideo, audiobook, shortFilm, tvShow, software, ebook, all
     *
     * @return the observable object that contains list of matched tracks based on the searched track.
     */
    fun searchTrack(term: String, country: String, media: String): Observable<SearchTrackResponse>

    /**
     * Function to get the last searched tracks from the database
     *
     * @return the observable object that contains list of cached tracks.
     */
    fun getLastSearchTrack(): Observable<List<TrackEntity>>

    /**
     * Function to save the searched tracks into the database
     */
    fun saveSearchTrack(searchTrackResponse: SearchTrackResponse)
}

class TrackRepositoryImpl(private val apiClient: TrackApiClient): TrackRepository {

    override fun searchTrack(term: String, country: String, media: String): Observable<SearchTrackResponse> {
        return apiClient.getService().searchTrack(term, country, media)
    }

    override fun getLastSearchTrack(): Observable<List<TrackEntity>> {
        return TrackDatabase.INSTANCE?.trackDao()?.getAllTracks() ?: Observable.just(emptyList())
    }

    override fun saveSearchTrack(searchTrackResponse: SearchTrackResponse) {
        TrackDatabase.INSTANCE?.trackDao()?.deleteTracks()
        TrackDatabase.INSTANCE?.trackDao()?.saveTracks(searchTrackResponse.results.map {
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
        })
    }
}