/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.di

import com.heliossoftwaredeveloper.trackclient.TrackApiClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Ruel N. Grajo on 01/16/2020.
 *
 * Dependency Module for Track API client
 */

@Module
class TrackClientModule {

    @Provides
    @Singleton
    fun provideApiClient(client: OkHttpClient.Builder) =
        TrackApiClient(client)
}