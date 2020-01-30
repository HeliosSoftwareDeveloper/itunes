/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import com.heliossoftwaredeveloper.common.util.navigate
import com.heliossoftwaredeveloper.common.util.showBackButton
import com.heliossoftwaredeveloper.trackui.TrackDetailsFragment
import com.heliossoftwaredeveloper.trackui.TrackListFragment
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity Class
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

class MainActivity : DaggerAppCompatActivity(), TrackListFragment.OnTrackListFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (layout_container is FrameLayout) {
            TrackListFragment.newInstance().navigate(supportFragmentManager, R.id.layout_container, false)
        }
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

        val trackDetailsFragment: TrackDetailsFragment? = supportFragmentManager.findFragmentById(R.id.layout_container_details) as TrackDetailsFragment?

        if (trackDetailsFragment == null) {
            supportActionBar?.showBackButton(true)
            TrackDetailsFragment.newInstance(trackItem).navigate(supportFragmentManager, R.id.layout_container, true)
        } else {
            trackDetailsFragment.updateContent(trackItem)
        }
    }
}
