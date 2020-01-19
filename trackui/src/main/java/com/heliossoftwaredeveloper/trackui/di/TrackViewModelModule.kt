/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.di

import androidx.lifecycle.ViewModel
import com.heliossoftwaredeveloper.common.util.SharedPreferencesManager
import com.heliossoftwaredeveloper.common.viewModel.ViewModelKey
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepository
import com.heliossoftwaredeveloper.trackui.viewModel.TrackDetailsViewModel
import com.heliossoftwaredeveloper.trackui.viewModel.TrackListViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * ViewModel module class that provides track ViewModels
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */
@Module
class TrackViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(TrackListViewModel::class)
    fun provideTrackListViewModel(
        trackRepository: TrackRepository,
        sharedPreferencesManager: SharedPreferencesManager
    ): ViewModel = TrackListViewModel(trackRepository, sharedPreferencesManager)

    @Provides
    @IntoMap
    @ViewModelKey(TrackDetailsViewModel::class)
    fun provideTrackDetailsViewModel(
    ): ViewModel = TrackDetailsViewModel()
}
