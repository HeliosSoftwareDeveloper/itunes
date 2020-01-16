/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient

import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Retrofit Service interface for Track API
 *
 * @author Ruel N. Grajo on 01/16/2020.
 */

interface TrackApiService {
    @GET("search")
    fun searchTrack(@Query("term") term: String, @Query("country") country: String, @Query("media") media: String): Observable<SearchTrackResponse>
}