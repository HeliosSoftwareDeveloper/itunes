/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heliossoftwaredeveloper.common.viewModel.BaseViewModel
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepository
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel class for Track List that handle transactions
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

class TrackListViewModel @Inject constructor(
    private val trackRepository: TrackRepository
) : BaseViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private val _trackListResult = MutableLiveData<List<TrackItem>?>()
    val trackListResult: LiveData<List<TrackItem>?> get() = _trackListResult

    fun searchMovie(keyword: String) {
        val disposable = trackRepository.searchTrack(keyword, DEFAULT_COUNTRY, DEFAULT_MEDIA)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _trackListResult.postValue(
                        result.results.map {
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
                    )
                },
                {
                    _trackListResult.postValue(null)
                }
            )
        compositeDisposable.add(disposable)
    }

    companion object {
        const val DEFAULT_COUNTRY = "AU"
        const val DEFAULT_MEDIA = "movie"
    }
}
