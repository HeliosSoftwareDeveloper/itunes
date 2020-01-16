/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.repository

import com.heliossoftwaredeveloper.trackclient.TrackApiClient
import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import io.reactivex.Observable

/**
 * Created by Ruel N. Grajo on 01/16/2020.
 *
 * Repository class for Track related stuff
 */

interface TrackRepository {
    /**
     * Function to execute search track by term, country, & media
     *
     * @param term the keyword of track that the user is looking for.
     * @param country the two-letter country code for the store. Example: US, AU, PH. The default is US.
     * @param media the media type you want to search. Example: movie, podcast, music, musicVideo, audiobook, shortFilm, tvShow, software, ebook, all
     *
     * @return the observable object that contains list of matched tracks based on the searched track.
     */
    fun searchTrack(term: String, country: String, media: String): Observable<SearchTrackResponse>
}

class TrackRepositoryImpl(private val apiClient: TrackApiClient): TrackRepository {

    override fun searchTrack(term: String, country: String, media: String): Observable<SearchTrackResponse> {
        return apiClient.getService().searchTrack(term, country, media)
    }
}