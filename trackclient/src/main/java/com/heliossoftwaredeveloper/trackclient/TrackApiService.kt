/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient

import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Ruel N. Grajo on 01/16/2020.
 *
 * Retrofit Service interface for Track API
 */

interface TrackApiService {
    @GET("search")
    fun searchTrack(@Query("term") term : String, @Query("country") country : String, @Query("media") media : String): Observable<SearchTrackResponse>
}