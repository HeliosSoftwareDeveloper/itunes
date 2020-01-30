/* (c) Helios Software Developer. All rights reserved. */
package com.heliossoftwaredeveloper.trackui

import android.os.Bundle
import com.heliossoftwaredeveloper.common.fragment.BaseFragment
import com.heliossoftwaredeveloper.common.util.formatStringDate
import com.heliossoftwaredeveloper.common.util.formatToDisplayTime
import com.heliossoftwaredeveloper.common.util.supportHTMLTags
import com.heliossoftwaredeveloper.trackui.databinding.TrackDetailsFragmentBinding
import com.heliossoftwaredeveloper.trackui.model.TrackItem
import com.heliossoftwaredeveloper.trackui.viewModel.TrackDetailsViewModel
import kotlinx.android.synthetic.main.track_details_fragment.*
import javax.inject.Inject

/**
 * Fragment that display the track details
 *
 * @author Ruel N. Grajo on 01/17/2020.
 */

class TrackDetailsFragment : BaseFragment<TrackDetailsFragmentBinding, TrackDetailsViewModel>() {

    @Inject
    override lateinit var viewModel: TrackDetailsViewModel

    private var selectedTrackItem: TrackItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedTrackItem = it.getSerializable(ARG_PARAM_ITEM) as TrackItem
        }
    }

    override fun createLayout() = R.layout.track_details_fragment

    override fun getBindingVariable() = BR.viewModel

    override fun initData() {}

    override fun initViews() {
        updateContent(selectedTrackItem)
    }

    override fun subscribeUi() {}

    fun updateContent(trackItem: TrackItem?) {
        selectedTrackItem = trackItem
        selectedTrackItem?.run {
            imageLoader.setImageURI(this.artworkUrl)
            imageBackgroundLoader.setImageURI(this.artworkUrl)
            imageBackgroundLoader.imageAlpha = 100

            trackNameTextView.text = this.trackName
            trackArtistTextView.text = getString(R.string.lbl_by, this.artistName)

            this.releaseDate?.let {
                releaseDateTextView.text = getString(R.string.lbl_release_date, it.formatStringDate()).supportHTMLTags()
            }
            this.trackTimeMillis?.let {
                trackLengthTextView.text = getString(R.string.lbl_track_length, it.formatToDisplayTime()).supportHTMLTags()
            }

            genreTextView.text = getString(R.string.lbl_track_genre, this.primaryGenreName).supportHTMLTags()

            priceTextView.text = getString(R.string.lbl_track_price, this.trackPrice.toString(), this.currency).supportHTMLTags()

            trackDescriptionTextView.text = this.longDescription
        }
    }

    companion object {
        private const val ARG_PARAM_ITEM = "argParamTrackItem"

        fun newInstance(trackItem: TrackItem): TrackDetailsFragment {
            val fragment = TrackDetailsFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM_ITEM, trackItem)
            fragment.arguments = args
            return fragment
        }
    }
}

