/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import android.os.Bundle
import android.view.MenuItem
import com.heliossoftwaredeveloper.common.util.navigate
import com.heliossoftwaredeveloper.common.util.showBackButton
import com.heliossoftwaredeveloper.trackui.TrackDetailsFragment
import com.heliossoftwaredeveloper.trackui.TrackListFragment
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import dagger.android.support.DaggerAppCompatActivity

/**
 * Main Activity Class
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

class MainActivity : DaggerAppCompatActivity(), TrackListFragment.OnTrackListFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TrackListFragment.newInstance().navigate(supportFragmentManager, R.id.layout_container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            supportActionBar?.showBackButton(false)
            supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportActionBar?.showBackButton(false)
    }

    override fun onTrackItemClicked(trackItem: TrackItem) {
        supportActionBar?.showBackButton(true)
        TrackDetailsFragment.newInstance(trackItem).navigate(supportFragmentManager, R.id.layout_container, true)
    }
}
