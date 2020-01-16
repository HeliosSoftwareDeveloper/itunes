/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.common.viewModel

import androidx.lifecycle.ViewModel

/**
 * ViewModel base class that will handle shared events/transactions to all ViewModels that will subclass this
 *
 * @author Ruel N. Grajo on 01/16/2020.
 */

abstract class BaseViewModel : ViewModel() {

    open fun onStart() {
    }

    open fun onResume() {

    }

    open fun onPause() {

    }

    open fun onStop() {

    }
}