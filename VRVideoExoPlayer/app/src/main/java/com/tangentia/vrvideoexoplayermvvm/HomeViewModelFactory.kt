package com.tangentia.vrvideoexoplayermvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory constructor(
    private val appContext: ApplicationClass,
//    private val repository: GrassdoorAPI,
//    private val db: GrassdoorDB
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(appContext
//                , this.repository, this.db
            ) as T
        } else {
            throw IllegalArgumentException("Viewmodel not found")
        }
    }
}