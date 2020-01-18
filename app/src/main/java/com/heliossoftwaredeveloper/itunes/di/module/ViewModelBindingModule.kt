/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.module

import androidx.lifecycle.ViewModelProvider
import com.heliossoftwaredeveloper.common.viewModel.ViewModelFactory
import com.heliossoftwaredeveloper.trackui.di.TrackViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Dependency Module for ViewModel Binding
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

@Module(
    includes = [
        TrackViewModelModule::class
    ]
)
abstract class ViewModelBindingModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}