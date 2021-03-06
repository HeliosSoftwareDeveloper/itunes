/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.module

import com.heliossoftwaredeveloper.applicationdatabase.di.DatabaseModule
import com.heliossoftwaredeveloper.common.di.CommonModule
import com.heliossoftwaredeveloper.common.di.NetworkModule
import com.heliossoftwaredeveloper.trackclient.di.TrackClientModule
import dagger.Module

/**
 * Dependency Module for Application Specific
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

@Module(
    includes = [
        CommonModule::class,
        NetworkModule::class,
        TrackClientModule::class,
        DatabaseModule::class
    ]
)
class AppModules
