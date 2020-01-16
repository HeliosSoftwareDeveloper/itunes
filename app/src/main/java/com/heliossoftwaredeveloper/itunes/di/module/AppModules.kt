/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.module

import com.heliossoftwaredeveloper.common.di.CommonModule
import com.heliossoftwaredeveloper.common.di.NetworkModule
import com.heliossoftwaredeveloper.trackclient.di.TrackClientModule
import dagger.Module

/**
 * Created by Ruel N. Grajo on 01/15/2020.
 *
 * Dependency Module for Application Specific
 */

@Module(
    includes = [
        CommonModule::class,
        NetworkModule::class,
        TrackClientModule::class
    ]
)
class AppModules
