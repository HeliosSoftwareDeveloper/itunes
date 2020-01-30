/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui

import android.content.Context
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.heliossoftwaredeveloper.common.fragment.BaseFragment
import com.heliossoftwaredeveloper.common.util.MarginItemDecoration
import com.heliossoftwaredeveloper.common.util.setLabelWithVisibility
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

class TrackListFragment : BaseFragment<TrackListFragmentBinding, TrackListViewModel>(), SearchView.OnQueryTextListener {

    @Inject
    override lateinit var viewModel: TrackListViewModel

    private var mListener: OnTrackListFragmentListener? = null

    override fun createLayout() = R.layout.track_list_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {}

    private lateinit var trackListAdapter : TrackListAdapter

    override fun initViews() {
        trackListAdapter = TrackListAdapter(object : TrackListAdapter.TrackListAdapterListener{
            override fun onTrackSelect(trackItem: TrackItem) {
                mListener?.onTrackItemClicked(trackItem)
            }
        })
        with (trackRecyclerView) {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            adapter = trackListAdapter
            addItemDecoration(MarginItemDecoration(resources.getDimension(R.dimen.space_unit_2).toInt(), SPAN_COUNT))
        }

        trackSearchView.setOnQueryTextListener(this)

        trackSwipeToRefresh.setOnRefreshListener {
            viewModel.searchMovie(null)
        }
    }

    override fun subscribeUi() {
        with (viewModel) {
            errorMessage.observe(viewLifecycleOwner, Observer { messageId ->
                invalidResultTextView.setLabelWithVisibility(messageId?.let {
                    getString(messageId)
                })
            })
            cacheLabel.observe(viewLifecycleOwner, Observer { label ->
                cachedLabelTextView.setLabelWithVisibility(label?.let {
                    getString(it.first, it.second)
                })
            })
            isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
                trackSwipeToRefresh.isRefreshing = isLoading
            })
            trackListResult.observe(viewLifecycleOwner, Observer { result ->
                trackListAdapter.updateDataSet(result)
            })
            getTrackFromCache()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTrackListFragmentListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onQueryTextSubmit(query: String?) = viewModel.handleQueryTextSubmit(query)

    override fun onQueryTextChange(newText: String?) = viewModel.handleQueryTextChange(newText)

    companion object {
        const val SPAN_COUNT = 2
        fun newInstance() = TrackListFragment()
    }

    /**
     * Interface to handle callbacks
     * */
    interface OnTrackListFragmentListener {
        /**
         * Function to handle list item clicked callback to class that implement it.
         *
         * @param trackItem the selected track item
         * */
        fun onTrackItemClicked(trackItem: TrackItem)
    }
}
