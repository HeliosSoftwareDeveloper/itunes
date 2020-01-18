/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.heliossoftwaredeveloper.trackui.TrackListFragment
import com.heliossoftwaredeveloper.trackui.viewModel.TrackListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Track Module class for fragment binding
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

@Module
abstract class TrackBindingModule {

    @ContributesAndroidInjector(
        modules = [
            InjectTrackListViewModel::class
        ]
    )
    abstract fun bindTrackListFragment(): TrackListFragment

    @Module
    class InjectTrackListViewModel {
        @Provides
        internal fun provideTrackListViewModel(
            factory: ViewModelProvider.Factory,
            target: TrackListFragment
        ) = ViewModelProviders.of(target, factory).get(TrackListViewModel::class.java)
    }

}