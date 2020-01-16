/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.di

import android.content.Context
import android.content.SharedPreferences
import com.heliossoftwaredeveloper.common.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dependency Module for shared components
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

@Module
class CommonModule(private val context: Context) {

    companion object {
        private const val STORE_NAME = "helios_itunes_shared_prefs"
    }

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharedPreferences() = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences) =
        SharedPreferencesManager(
            sharedPreferences
        )
}
