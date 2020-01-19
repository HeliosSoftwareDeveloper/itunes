/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackclient.repository

import com.heliossoftwaredeveloper.trackclient.BaseTrackTest
import com.heliossoftwaredeveloper.trackclient.RxImmediateSchedulerRule
import io.reactivex.Observable
import junit.framework.TestCase.assertEquals
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

/**
 * Unit-test class for Track Repository
 *
 * Created by Ruel N. Grajo on 01/19/2020.
 */

class TrackRepositoryTest : BaseTrackTest(){

    @Rule @JvmField val rule = MockitoJUnit.rule()!!
    @Rule @JvmField var testSchedulerRule =
        RxImmediateSchedulerRule()

    @Mock private lateinit var repository: TrackRepository

    @Test
    fun testValidSearchTrackList() {
        Mockito.`when`(repository.searchTrack("star", "au", "media")).thenReturn(Observable.just(validSearchTrackMockResponse()))

        repository.searchTrack("star", "au", "media").subscribe{

            // test the size of the parsed result
            assertEquals(2, it.resultCount)

            // test the first data if parsed correctly
            with (it.results.first()) {
                assertEquals("George Lucas", this.artistName)
                assertEquals("Star Wars: The Phantom Menace", this.trackName)
                assertEquals(18.99, this.trackPrice)
                assertEquals("Action & Adventure", this.primaryGenreName)
                assertEquals("https://is2-ssl.mzstatic.com/image/thumb/Video123/v4/18/98/ce/1898cea4-56a5-0542-c115-f1057ed45fea/pr_source.lsr/100x100bb.jpg", this.artworkUrl100)
            }

            // test the last data if parsed correctly
            with (it.results.last()) {
                assertEquals("George Lucas", this.artistName)
                assertEquals("Star Wars: Revenge of the Sith", this.trackName)
                assertEquals(18.99, this.trackPrice)
                assertEquals("Action & Adventure", this.primaryGenreName)
                assertEquals("https://is5-ssl.mzstatic.com/image/thumb/Video113/v4/f6/ed/2e/f6ed2e90-8c9a-0a71-d87b-3e502c999ce7/pr_source.lsr/100x100bb.jpg", this.artworkUrl100)
            }
        }
    }

    @Test
    fun testEmptySearchTrackList() {
        Mockito.`when`(repository.searchTrack("star", "au", "media")).thenReturn(Observable.just(emptySearchTrackMockResponse()))

        repository.searchTrack("star", "au", "media").subscribe{
            // test the size of the parsed result
            assertEquals(0, it.resultCount)
        }
    }

    @Test
    fun testInvalidSearchTrackList() {
        Mockito.`when`(repository.searchTrack("star", "au", "media")).thenReturn(Observable.just(invalidSearchTrackMockResponse()))

        repository.searchTrack("star", "au", "media").subscribe{
            // test the size of the parsed result
            assertEquals(0, it.resultCount)
        }
    }

    @Test
    fun testNoLastSearchedTrackList() {
        Mockito.`when`(repository.getLastSearchTrack()).thenReturn(Observable.just(emptyList()))

        repository.getLastSearchTrack().subscribe{
            // test the size of the parsed result
            assertEquals(0, it.size)
        }
    }

    @Test
    fun testValidLastSearchedTrackList() {
        Mockito.`when`(repository.getLastSearchTrack()).thenReturn(Observable.just(validTrackEntityList()))

        repository.getLastSearchTrack().subscribe{
            // test the size of the parsed result
            assertEquals(1, it.size)

            // test the first data if the track details are correct
            with (it.first()) {
                assertEquals("George Lucas", this.artistName)
                assertEquals("Star Wars: The Phantom Menace", this.trackName)
                assertEquals(18.99, this.trackPrice)
                assertEquals("Action & Adventure", this.primaryGenreName)
                assertEquals("https://is2-ssl.mzstatic.com/image/thumb/Video123/v4/18/98/ce/1898cea4-56a5-0542-c115-f1057ed45fea/pr_source.lsr/100x100bb.jpg", this.artworkUrl)
            }
        }
    }
}
