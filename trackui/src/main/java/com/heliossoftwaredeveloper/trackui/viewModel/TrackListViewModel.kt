/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heliossoftwaredeveloper.common.util.SharedPreferencesManager
import com.heliossoftwaredeveloper.common.viewModel.BaseViewModel
import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepository
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import com.heliossoftwaredeveloper.trackui.util.entityModelToTrackItem
import com.heliossoftwaredeveloper.trackui.util.serviceModelToTrackEntity
import com.heliossoftwaredeveloper.trackui.util.serviceModelToTrackItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel class for Track List that handle transactions
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

class TrackListViewModel @Inject constructor(
    private val trackRepository: TrackRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : BaseViewModel() {

    private val _trackListResult = MutableLiveData<List<TrackItem>?>()
    val trackListResult: LiveData<List<TrackItem>?> get() = _trackListResult

    private val _trackListCached = MutableLiveData<List<TrackItem>?>()
    val trackListCached: LiveData<List<TrackItem>?> get() = _trackListCached

    private var trackCachedDisposable : Disposable? = null
    private var trackSearchDisposable : Disposable? = null
    private var saveTrackDisposable : Disposable? = null

    /**
     * Function to execute search track on track-repository
     *
     * @param keyword the keyword of track that the user is looking for.
     */
    fun searchMovie(keyword: String) {
        trackSearchDisposable = trackRepository.searchTrack(keyword, DEFAULT_COUNTRY, DEFAULT_MEDIA)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { it?.saveTrackResponse(keyword) }
            .subscribe(
                { result ->
                    _trackListResult.postValue(result.results.serviceModelToTrackItem())
                    trackSearchDisposable?.dispose()
                },
                {
                    _trackListResult.postValue(null)
                    trackSearchDisposable?.dispose()
                }
            )
    }

    /**
     * Extension Function to execute save searched track on track-repository
     *
     * @param keyword the keyword of track that the user is looking for.
     */
    private fun SearchTrackResponse.saveTrackResponse(keyword: String) {
        saveTrackDisposable = Observable.fromCallable {
            // Save the keyword to shared preference
            sharedPreferencesManager.stringValue(SharedPreferencesManager.LAST_KEYWORD_SEARCHED, keyword)
            // Save the search response to database
            trackRepository.saveSearchTrack(this.results.serviceModelToTrackEntity())
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                saveTrackDisposable?.dispose()
            }
    }

    /**
     * Function to execute get last searched track cached on track-repository
     */
    fun getTrackFromCache() {
        trackCachedDisposable = trackRepository.getLastSearchTrack()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _trackListCached.postValue(result.entityModelToTrackItem())
                    trackCachedDisposable?.dispose()
                },
                {
                    _trackListCached.postValue(null)
                    trackCachedDisposable?.dispose()
                }
            )
    }

    companion object {
        const val DEFAULT_COUNTRY = "AU"
        const val DEFAULT_MEDIA = "movie"
    }
}
