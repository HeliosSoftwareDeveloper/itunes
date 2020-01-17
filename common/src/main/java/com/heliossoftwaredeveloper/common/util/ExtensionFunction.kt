/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * File that contains extension functions
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

/**
 * Extension function that show and replace a fragment
 *
 * @param supportFragmentManager fragment manager class that is needed to start a fragment transaction
 * @param containerId the layout container where the fragment is inflated
 * @param isAddToBackStack the boolean flag to add the fragment into the back stack
 *
 * @return the attached fragment
 */
fun Fragment.navigate(supportFragmentManager: FragmentManager, containerId: Int, isAddToBackStack: Boolean) : Fragment {
    supportFragmentManager.beginTransaction().let {
        it.replace(containerId, this, this.javaClass.name)
        if (isAddToBackStack) {
            it.addToBackStack(this.javaClass.name)
        }
        it.show(this)
        it.commit()
    }
    return this
}