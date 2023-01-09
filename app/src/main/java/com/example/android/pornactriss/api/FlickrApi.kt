package com.example.android.pornactriss.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface FlickrApi {

    @GET("pornstars/?format=json&max_rank=200&min_rank=151&rapidapi-key=8d80e164ebmshc0321abcc02a47fp1cf1d5jsnb9a953effe48")
    fun fetchPhotos(): Call<FlickrResponse>

    @GET
    fun fetchUrlBytes(@Url url: String): Call<ResponseBody>
}