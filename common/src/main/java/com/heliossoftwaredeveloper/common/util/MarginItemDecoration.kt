/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Utility class that add proper margin to grid items on recyclerView
 *
 * @author Ruel N. Grajo on 01/18/2020.
 */

class MarginItemDecoration(private val space: Int, private val spanCount: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val itemPosition = parent.getChildAdapterPosition(view)
        val itemColumn = itemPosition % spanCount

        with(outRect) {
            left = itemColumn * space / spanCount
            right = space - (itemColumn + 1) * space / spanCount
            if (itemPosition >= spanCount) {
                top = space
            }
        }
    }
}