/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.util

import android.content.SharedPreferences

/**
 * Manager class to handle SharedPreferences
 *
 * @author Ruel N. Grajo on 01/15/2020.
 */

class SharedPreferencesManager(private val sharedPreferences: SharedPreferences) {
    companion object {
        const val LAST_DATE_SYNC = "lastDateSync"
        const val LAST_KEYWORD_SEARCHED = "lastKeywordSearched"
    }

    fun stringValue(key: String, value: String? = null) : String {
        value?.run {
            with(sharedPreferences.edit()) {
                putString(key, value)
                apply()
            }
        }
        return sharedPreferences.getString(key, "") ?: ""
    }
}
