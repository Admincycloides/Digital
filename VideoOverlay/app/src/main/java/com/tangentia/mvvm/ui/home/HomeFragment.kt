package com.tangentia.mvvm.ui.home

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.tangentia.mvvm.ApplicationClass
import com.tangentia.mvvm.ApplicationClass.Companion.appContext
import com.tangentia.mvvm.R
import com.tangentia.mvvm.databinding.FragmentHomeBinding
import com.tangentia.mvvm.utilities.changeTintColor

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            requireContext().applicationContext as ApplicationClass
        )
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var exoPlayer: SimpleExoPlayer

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //context
        val application = requireNotNull(this.activity).application

        val view = binding.root

        //listeners
        binding.overlayColors.redOverlay.setOnClickListener {
            binding.overlayLayout.visibility = View.VISIBLE
            binding.overlayLayout.changeTintColor(appContext.getColor(R.color.red))
        }

        binding.overlayColors.yellowOverlay.setOnClickListener {
            binding.overlayLayout.visibility = View.VISIBLE
            binding.overlayLayout.changeTintColor(appContext.getColor(R.color.yellow))
        }

        binding.overlayColors.greenOverlay.setOnClickListener {
            binding.overlayLayout.visibility = View.VISIBLE
            binding.overlayLayout.changeTintColor(appContext.getColor(R.color.green))
        }

        binding.overlayColors.clearOverlay.setOnClickListener {
            binding.overlayLayout.visibility = View.GONE
            binding.overlayLayout.changeTintColor(appContext.getColor(R.color.white))
        }

        (binding.simpleExoPlayerView.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

        setText()
        return view
    }

    private fun setText() {
        try {
            //bandwisthmeter is used for getting default bandwidth
            val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
            // track selector is used to navigate between video using a default seekbar.
            val trackSelector: TrackSelector =
                DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
            //we are ading our track selector to exoplayer.
            exoPlayer = ExoPlayerFactory.newSimpleInstance(appContext, trackSelector)
            // we are parsing a video url and parsing its video uri.
            val videouri = Uri.parse(viewModel.videoUrl)
            // we are creating a variable for datasource factory and setting its user agent as 'exoplayer_view'
            val dataSourceFactory = DefaultHttpDataSourceFactory("exoplayer_video")
            // we are creating a variable for extractor factory and setting it to default extractor factory.
            val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
            //we are creating a media source with above variables and passing our event handler as null,
            val mediaSource: MediaSource =
                ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null)
            //inside our exoplayer view we are setting our player
            binding.simpleExoPlayerView.player = exoPlayer
            //we are preparing our exoplayer with media source.
            exoPlayer.prepare(mediaSource)
            //we are setting our exoplayer when it is ready.
            exoPlayer.playWhenReady = true
        } catch (e: Exception) {
            // below line is used for handling our errors.
            Log.e("TAG", "Error : $e")
        }
    }

    override fun onResume() {
        super.onResume()
        binding.simpleExoPlayerView.onResume()
    }
}