/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.module

import com.heliossoftwaredeveloper.itunes.MainActivity
import com.heliossoftwaredeveloper.trackui.di.TrackBindingModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dependency Module for Activity Binding
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(
        modules = [
            TrackBindingModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}
