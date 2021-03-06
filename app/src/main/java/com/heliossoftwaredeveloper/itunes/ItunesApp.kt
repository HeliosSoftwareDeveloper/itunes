/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import androidx.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.heliossoftwaredeveloper.common.di.CommonModule
import com.heliossoftwaredeveloper.common.di.NetworkModule
import com.heliossoftwaredeveloper.applicationdatabase.di.DatabaseModule
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
        Fresco.initialize(this)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val context = applicationContext

        return DaggerApplicationComponent
            .builder()
            .application(this)
            .networkModule(NetworkModule())
            .trackClientModule(TrackClientModule())
            .commonModule(CommonModule(context))
            .databaseModule(DatabaseModule(this))
            .build()
    }
}
