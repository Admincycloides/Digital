package com.tangentia.vrvideoexoplayermvvm

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.spherical.SphericalGLSurfaceView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.tangentia.vrvideoexoplayermvvm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    private lateinit var binding: ActivityMainBinding
//    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.video_player_layout)

//        (binding.playerView.videoSurfaceView as SphericalGLSurfaceView)
//            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)

//        (binding.playerView.videoSurfaceView as SphericalSurfaceView)
//            .setDefaultStereoMode(stereoMode)


        (binding.playerView.videoSurfaceView as SphericalGLSurfaceView)
            .setDefaultStereoMode(C.STEREO_MODE_TOP_BOTTOM)
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Build.VERSION.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(this, "javiermarsicano-VR-app")
        // Create a media source using the supplied URI
        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(this).build()

        val uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4")
//        val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource)

        binding.playerView.player = player
        binding.playerView.onResume()
    }

    private fun releasePlayer() {
        binding.playerView.onPause()
        player?.release()
        player = null
    }
}