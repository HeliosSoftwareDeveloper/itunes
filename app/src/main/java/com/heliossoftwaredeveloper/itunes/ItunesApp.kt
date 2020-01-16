/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import com.heliossoftwaredeveloper.common.di.CommonModule
import com.heliossoftwaredeveloper.common.di.NetworkModule
import com.heliossoftwaredeveloper.itunes.di.component.DaggerApplicationComponent
import com.heliossoftwaredeveloper.trackclient.di.TrackClientModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Application Class
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

class ItunesApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val context = applicationContext

        return DaggerApplicationComponent
            .builder()
            .application(this)
            .networkModule(
                NetworkModule()
            )
            .trackClientModule(TrackClientModule())
            .commonModule(
                CommonModule(
                    context
                )
            )
            .build()
    }
}
