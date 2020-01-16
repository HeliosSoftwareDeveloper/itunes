/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ruel N. Grajo on 01/16/2020.
 *
 * Client class to Intercept request
 */

class TrackApiClient(okHttp: OkHttpClient.Builder) : Interceptor, Authenticator {

    private var retrofit: Retrofit

    init {
        okHttp.addInterceptor(this)
        okHttp.authenticator(this)
        retrofit = Retrofit.Builder()
            .client(okHttp.build())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_HTTP_URL)
            .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader(HEADER_CONTENT_TYPE, HEADER_VALUE_APP_JSON)
            .addHeader(HEADER_ACCEPT, HEADER_VALUE_APP_JSON)
            .build()
        return chain.proceed(request)
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        try {
            return response.request().newBuilder().build()
        } catch (ex: Exception) { }
        return null
    }

    fun getService(): TrackApiService {
        return retrofit.create(TrackApiService::class.java)
    }

    companion object {
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_ACCEPT = "Accept"
        const val HEADER_VALUE_APP_JSON = "application/json"
    }
}