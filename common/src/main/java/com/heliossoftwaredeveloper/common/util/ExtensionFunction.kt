/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.util

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.reactivex.disposables.Disposable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

/**
 * Extension function for string to HTML tags
 *
 * @return the spanned string
 */
fun String.supportHTMLTags() : Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this)
    }
}

/**
 * Extension function to convert milliseconds to time display format
 *
 * @return the formatted time string
 */
fun Long.formatToDisplayTime() : String {
    val seconds = this / 1000 % 60
    val minutes = this / (1000 * 60) % 60
    val hours = this / (1000 * 60 * 60)

    return "$hours:$minutes:$seconds"
}

private const val DISPLAY_DATE_FORMAT = "MMM. d, yyyy"
private const val DISPLAY_SERVER_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

/**
 * Extension function to format server date format to display date format
 *
 * @return the formatted time string
 */
fun String.formatStringDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat(DISPLAY_SERVER_DATE_TIME_FORMAT, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        simpleDateFormat.parse(this)?.let {
            SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.getDefault()).format(it)
        } ?: "Not Available"
    } catch (e: ParseException) {
        "Not Available"
    }
}

/**
 * Extension function to show or hide the back button
 *
 * @param shouldShow flag to set if the back button should be display or not
 */
fun ActionBar.showBackButton(shouldShow: Boolean) {
    this.setDisplayHomeAsUpEnabled(shouldShow)
    this.setDisplayShowHomeEnabled(shouldShow)
}

/**
 * Extension function to set the text value of textView. It also set the visibility depending on the message value
 *
 * @param message the informative message to display
 */
fun AppCompatTextView.setLabelWithVisibility(message: String?) {
    with(this) {
        visibility = message?.let {
            text = message
            View.VISIBLE
        } ?: View.GONE
    }
}

/**
 * Extension function to handle rxJava disposal safely
 *
 * @return boolean object to state the result of safe dispose
 */
fun Disposable?.safeDispose() = if (this != null && !isDisposed) {
    dispose()
    true
} else false