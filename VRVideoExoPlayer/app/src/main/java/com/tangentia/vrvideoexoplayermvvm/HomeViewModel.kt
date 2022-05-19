package com.tangentia.vrvideoexoplayermvvm

import android.util.Log
import androidx.lifecycle.AndroidViewModel

class HomeViewModel(
    private val appContext: ApplicationClass,
//    private val repository: GrassdoorAPI,
//    private val db: GrassdoorDB
) : AndroidViewModel(appContext) {

    val videoUrl = "https://storage.googleapis.com/exoplayer-test-media-1/360/congo.mp4"

    init {
        Log.i("HomeVM", "HomeVM created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("HomeVM", "HomeVM destroyed")
    }
}