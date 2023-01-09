package com.example.android.pornactriss

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel: ViewModel() {

    val resultsLiveData: LiveData<List<Girls>>

    init {
        resultsLiveData = FlickrFetchr().fetchPhotos()
    }
}