/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heliossoftwaredeveloper.common.util.SharedPreferencesManager
import com.heliossoftwaredeveloper.common.util.safeDispose
import com.heliossoftwaredeveloper.common.viewModel.BaseViewModel
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepository
import com.heliossoftwaredeveloper.trackui.R
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import com.heliossoftwaredeveloper.trackui.util.entityModelToTrackItem
import com.heliossoftwaredeveloper.trackui.util.serviceModelToTrackItem
import io.reactivex.disposables.Disposable
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

    private val _trackListResult = MutableLiveData<List<TrackItem>>()
    val trackListResult: LiveData<List<TrackItem>> get() = _trackListResult

    private val _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?> get() = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _cacheLabel = MutableLiveData<Pair<Int, String>?>()
    val cacheLabel: LiveData<Pair<Int, String>?> get() = _cacheLabel

    private var trackCachedDisposable : Disposable? = null
    private var trackSearchDisposable : Disposable? = null

    /**
     * Function to handle SearchView on text change event
     *
     * @param newText the current string value of SearchView.
     *
     * @return false to let the SearchView perform the default action.
     */
    fun handleQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()) {
            _errorMessage.postValue(null)
        }
        return false
    }

    /**
     * Function to handle SearchView on text submit event
     *
     * @param query the current query string value of SearchView.
     *
     * @return false to let the SearchView perform the default action.
     */
    fun handleQueryTextSubmit(query: String?): Boolean {
        query?.let {
            searchMovie(it)
        } ?: _errorMessage.postValue(R.string.error_empty_query)
        return false
    }

    /**
     * Function to execute search track on track-repository
     *
     * @param keyword the keyword of track that the user is looking for.
     */
    fun searchMovie(keyword: String?) {
        val term = keyword ?: sharedPreferencesManager.stringValue(SharedPreferencesManager.LAST_KEYWORD_SEARCHED)
        _isLoading.postValue(true)
        trackSearchDisposable = trackRepository.searchTrack(term, DEFAULT_COUNTRY, DEFAULT_MEDIA)
            .doFinally {
                // Save the keyword to shared preference
                sharedPreferencesManager.stringValue(SharedPreferencesManager.LAST_KEYWORD_SEARCHED, keyword)
                _cacheLabel.postValue(null)
                _isLoading.postValue(false)
            }
            .subscribe(
                { result ->
                    _errorMessage.postValue(
                        if (result.resultCount > 0) null
                        else R.string.error_no_track_match
                    )
                    _trackListResult.postValue(result.results.serviceModelToTrackItem())
                    trackSearchDisposable?.safeDispose()
                },
                {
                    _errorMessage.postValue(R.string.error_network_connection)
                    _trackListResult.postValue(emptyList())
                    trackSearchDisposable?.safeDispose()
                }
            )
    }

    /**
     * Function to execute get last searched track cached on track-repository
     */
    fun getTrackFromCache() {
        if (trackListResult.value != null)
            return
        trackCachedDisposable = trackRepository.getLastSearchTrack()
            .subscribe(
                { result ->
                    _cacheLabel.postValue(
                        if (result.isNotEmpty())
                            Pair(R.string.lbl_cached, sharedPreferencesManager.stringValue(
                                SharedPreferencesManager.LAST_KEYWORD_SEARCHED
                            ))
                        else null
                    )
                    _trackListResult.postValue(result.entityModelToTrackItem())
                    _errorMessage.postValue(null)
                    trackCachedDisposable?.safeDispose()
                },
                {
                    _errorMessage.postValue(R.string.error_database)
                    _cacheLabel.postValue(null)
                    trackCachedDisposable?.safeDispose()
                }
            )
    }

    companion object {
        const val DEFAULT_COUNTRY = "AU"
        const val DEFAULT_MEDIA = "movie"
    }
}
