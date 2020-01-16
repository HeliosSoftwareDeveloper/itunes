/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Dependency Module for network related stuffs
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideHttpClientBuilder() = OkHttpClient.Builder()
}