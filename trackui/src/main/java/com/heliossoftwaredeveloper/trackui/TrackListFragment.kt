/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.heliossoftwaredeveloper.common.fragment.BaseFragment
import com.heliossoftwaredeveloper.common.util.MarginItemDecoration
import com.heliossoftwaredeveloper.common.util.SharedPreferencesManager
import com.heliossoftwaredeveloper.common.util.SharedPreferencesManager.Companion.LAST_KEYWORD_SEARCHED
import com.heliossoftwaredeveloper.trackui.databinding.TrackListFragmentBinding
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import com.heliossoftwaredeveloper.trackui.viewModel.TrackListViewModel
import kotlinx.android.synthetic.main.track_list_fragment.*
import javax.inject.Inject

/**
 * Fragment that display the track list
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

class TrackListFragment : BaseFragment<TrackListFragmentBinding, TrackListViewModel>(){

    @Inject
    override lateinit var viewModel: TrackListViewModel

    @Inject
    lateinit var sharedPreferencesManager: SharedPreferencesManager

    companion object {
        const val SPAN_COUNT = 2
        fun newInstance() = TrackListFragment()
    }

    override fun createLayout() = R.layout.track_list_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {}

    lateinit var trackListAdapter : TrackListAdapter
    override fun initViews() {
        trackListAdapter = TrackListAdapter(object : TrackListAdapter.TrackListAdapterListener{
            override fun onTrackSelect(track: TrackItem) {

            }
        })
        with (trackRecyclerView) {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = trackListAdapter
            addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.space_unit_2).toInt(), SPAN_COUNT))
        }

        trackSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchMovie(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    invalidResultTextView.visibility = View.GONE
                }
                return false
            }
        })
    }

    override fun subscribeUi() {
        viewModel.trackListCached.observe(this, Observer { result ->
            result?.run {
                if (this.isNotEmpty()) {
                    cachedLabelTextView.visibility = View.VISIBLE
                    cachedLabelTextView.text = getString(R.string.lbl_cached, sharedPreferencesManager.stringValue(LAST_KEYWORD_SEARCHED))
                    trackListAdapter.updateDataSet(this)
                }
            } ?: showErrorMessage(getString(R.string.error_database))
        })
        viewModel.trackListResult.observe(this, Observer { result ->
            cachedLabelTextView.visibility = View.GONE
            result?.run {
                if (this.isNotEmpty()) {
                    trackListAdapter.updateDataSet(this)
                    invalidResultTextView.visibility = View.GONE
                }
                else {
                    showErrorMessage(getString(R.string.error_no_track_match))
                }
            } ?: showErrorMessage(getString(R.string.error_network_connection))
        })
        viewModel.getTrackFromCache()
    }

    /**
     * Function to show error message
     *
     * @param message the informative message to display
     */
    private fun showErrorMessage(message: String) {
        trackListAdapter.updateDataSet(emptyList())
        with(invalidResultTextView) {
            text = message
            visibility = View.VISIBLE
        }
    }
}
