/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.module

import com.heliossoftwaredeveloper.common.CommonModule
import com.heliossoftwaredeveloper.common.NetworkModule
import dagger.Module

/**
 * Created by Ruel N. Grajo on 01/15/2020.
 *
 * Dependency Module for Application Specific
 */

@Module(
    includes = [
        CommonModule::class,
        NetworkModule::class
    ]
)
class AppModules
