/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.itunes

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.heliossoftwaredeveloper.common.util.navigate
import com.heliossoftwaredeveloper.trackui.TrackListFragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * Main Activity Class
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

class MainActivity : DaggerAppCompatActivity() {

    var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentFragment = TrackListFragment.newInstance().navigate(supportFragmentManager, R.id.layout_container, false)
    }
}
