package com.tangentia.mvvm.ui.home

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.tangentia.mvvm.ApplicationClass

class HomeViewModel(
    private val appContext: ApplicationClass,
//    private val repository: GrassdoorAPI,
//    private val db: GrassdoorDB
) : AndroidViewModel(appContext) {

    init {
        Log.i("HomeVM", "HomeVM created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("HomeVM", "HomeVM destroyed")
    }
}