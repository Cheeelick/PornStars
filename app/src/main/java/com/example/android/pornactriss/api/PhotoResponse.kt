package com.example.android.pornactriss.api

import android.widget.Gallery
import com.example.android.pornactriss.Girls
import com.example.android.pornactriss.fragments.GirlListFragment
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    lateinit var images: List<Girls>
}