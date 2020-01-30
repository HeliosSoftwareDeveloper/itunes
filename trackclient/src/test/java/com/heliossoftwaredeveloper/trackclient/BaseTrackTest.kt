/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient

import com.google.gson.Gson
import com.heliossoftwaredeveloper.applicationdatabase.entity.TrackEntity
import com.heliossoftwaredeveloper.trackclient.model.SearchTrackResponse
import java.io.BufferedReader
import java.io.FileReader

/**
 * Base Unit-test class for Track flow
 *
 * Created by Ruel N. Grajo on 01/19/2019.
 */

open class BaseTrackTest {

    /**
     * Base class function to get valid mock trackList response
     *
     * @return SearchTrackResponse valid mock track response
     * */
    fun validSearchTrackMockResponse() : SearchTrackResponse {
        return loadMockDataFromResource("mock_data_valid_get_movie_response.json", SearchTrackResponse::class.java) as SearchTrackResponse
    }

    /**
     * Base class function to get invalid mock trackList response
     *
     * @return SearchTrackResponse invalid mock track response
     * */
    fun invalidSearchTrackMockResponse() : SearchTrackResponse {
        return loadMockDataFromResource("mock_data_invalid_get_movie_response.json", SearchTrackResponse::class.java) as SearchTrackResponse
    }

    /**
     * Base class function to get empty mock trackList response
     *
     * @return SearchTrackResponse empty mock track response
     * */
    fun emptySearchTrackMockResponse() : SearchTrackResponse {
        return loadMockDataFromResource("mock_data_empty_get_movie_response.json", SearchTrackResponse::class.java) as SearchTrackResponse
    }

    /**
     * Base class function to get valid mock trackList entity from database
     *
     * @return List<TrackEntity> valid mock trackList entity
     * */
    fun validTrackEntityList() : List<TrackEntity> {
        val trackEntity = TrackEntity(
            id = 0,
            kind = "feature-movie",
            trackId = 975080816,
            artistName = "George Lucas",
            collectionName = null,
            trackName = "Star Wars: The Phantom Menace",
            artworkUrl = "https://is2-ssl.mzstatic.com/image/thumb/Video123/v4/18/98/ce/1898cea4-56a5-0542-c115-f1057ed45fea/pr_source.lsr/100x100bb.jpg",
            collectionPrice = null,
            trackPrice = 18.99,
            trackRentalPrice = null,
            collectionHdPrice = null,
            trackHdPrice = null,
            trackHdRentalPrice = null,
            releaseDate = "1999-05-19T07:00:00Z",
            collectionExplicitness = null,
            trackExplicitness = null,
            trackCount = 1,
            trackNumber = 1,
            trackTimeMillis = 8180628,
            country = "AUS",
            currency = "AUD",
            primaryGenreName = "Action & Adventure",
            contentAdvisoryRating = null,
            shortDescription = null,
            longDescription = "Sample long description"
        )
        return listOf(trackEntity)
    }

    /**
     * Base class function to get mock trackList response
     *
     * @param fileName name of the mock data file
     * @param classType the expected class type of response
     *
     * @return SearchTrackResponse mock track response
     * */
    private fun loadMockDataFromResource(fileName : String, classType : Class<out Any>) : Any? {
        return javaClass.classLoader?.getResource(fileName)?.let { sourcePath ->
            val bufferedReader = BufferedReader(FileReader(sourcePath.path))
            Gson().fromJson(bufferedReader, classType)
        }
    }

}