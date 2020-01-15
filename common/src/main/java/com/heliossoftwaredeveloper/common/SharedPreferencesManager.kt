/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common

import android.content.SharedPreferences

/**
 * Created by Ruel N. Grajo on 01/15/2020.
 *
 * Manager class to handle SharedPreferences
 */

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val LAST_DATE_SYNC = "lastDateSync"
    }
}
