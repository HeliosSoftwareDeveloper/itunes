/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes.di.component

import android.app.Application
import com.heliossoftwaredeveloper.common.di.CommonModule
import com.heliossoftwaredeveloper.common.di.NetworkModule
import com.heliossoftwaredeveloper.itunes.ItunesApp
import com.heliossoftwaredeveloper.itunes.di.module.AppModules
import com.heliossoftwaredeveloper.itunes.di.module.ViewModelBindingModule
import com.heliossoftwaredeveloper.itunes.di.module.ActivityBindingModule
import com.heliossoftwaredeveloper.trackclient.di.TrackClientModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Created by Ruel N. Grajo on 01/15/2020.
 *
 * Application's Dependency Component
 */

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelBindingModule::class,
        AppModules::class
    ]
)
interface ApplicationComponent : AndroidInjector<ItunesApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun trackClientModule(trackClientModule: TrackClientModule): Builder
        fun commonModule(commonModule: CommonModule): Builder
        fun build(): ApplicationComponent
    }
}
