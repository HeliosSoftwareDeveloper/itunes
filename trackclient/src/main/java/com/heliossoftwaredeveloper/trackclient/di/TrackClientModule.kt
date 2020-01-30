/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.di

import com.heliossoftwaredeveloper.applicationdatabase.dao.TrackDao
import com.heliossoftwaredeveloper.trackclient.TrackApiClient
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepository
import com.heliossoftwaredeveloper.trackclient.repository.TrackRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Dependency Module for Track API client
 *
 * @author Ruel N. Grajo on 01/16/2020.
 */

@Module
class TrackClientModule {

    @Provides
    @Singleton
    fun provideApiClient(client: OkHttpClient.Builder) = TrackApiClient(client)

    @Provides
    @Singleton
    fun provideTrackRepository(apiClient: TrackApiClient, trackDao: TrackDao): TrackRepository = TrackRepositoryImpl(apiClient, trackDao)
}