/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import com.heliossoftwaredeveloper.common.CommonModule
import com.heliossoftwaredeveloper.common.NetworkModule
import com.heliossoftwaredeveloper.itunes.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Ruel N. Grajo on 01/15/2020.
 *
 * Application Class
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
            .commonModule(CommonModule(context))
            .build()
    }
}
