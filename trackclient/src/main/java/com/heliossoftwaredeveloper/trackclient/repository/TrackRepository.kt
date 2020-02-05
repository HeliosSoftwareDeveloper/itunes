/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.repository

import com.heliossoftwaredeveloper.applicationdatabase.dao.TrackDao
import com.heliossoftwaredeveloper.applicationdatabase.entity.TrackEntity
import com.heliossoftwaredeveloper.common.util.safeDispose
import com.heliossoftwaredeveloper.trackclient.TrackApiClient
import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import com.heliossoftwaredeveloper.trackclient.serviceModelToTrackEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
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
     *
     * @param trackList the list of track entity to save in database.
     */
    fun saveSearchTrack(trackList: List<TrackEntity>?)
}

class TrackRepositoryImpl(private val apiClient: TrackApiClient, private val trackDao: TrackDao): TrackRepository {

    private var saveTrackDisposable : Disposable? = null

    override fun searchTrack(term: String, country: String, media: String): Observable<SearchTrackResponse> {
        return apiClient.getService().searchTrack(term, country, media)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { saveSearchTrack(it?.results?.serviceModelToTrackEntity()) }
    }

    override fun getLastSearchTrack(): Observable<List<TrackEntity>> {
        return trackDao.getAllTracks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveSearchTrack(trackList: List<TrackEntity>?){
        saveTrackDisposable = Observable.fromCallable {
            trackDao.deleteTracks()
            trackList?.let {
                trackDao.saveTracks(it)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                saveTrackDisposable?.safeDispose()
            }
    }
}