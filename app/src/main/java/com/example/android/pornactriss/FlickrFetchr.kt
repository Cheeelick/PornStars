package com.example.android.pornactriss

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.nfc.Tag
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.pornactriss.api.FlickrApi
import com.example.android.pornactriss.api.FlickrResponse
import com.example.android.pornactriss.api.PhotoResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://papi-pornstarsapi.p.rapidapi.com/"
private const val TAG = "FlickrFetchr"


class FlickrFetchr {

    private val flickrApi: FlickrApi


    init{
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        flickrApi = retrofit.create(FlickrApi::class.java)
    }


    fun fetchPhotos(): LiveData<List<Girls>> {
        val responseLiveData: MutableLiveData<List<Girls>> = MutableLiveData()
        val flickrRequest: Call<FlickrResponse> = flickrApi.fetchPhotos()

        flickrRequest.enqueue(object: Callback<FlickrResponse> {
            override fun onFailure(call: Call<FlickrResponse>, t: Throwable){
                Log.e(TAG, "Failed to fetch photos", t)
            }


            override fun onResponse(
                call: Call<FlickrResponse>,
                response: Response<FlickrResponse>
            ) {
                Log.d(TAG, "Response received")
                val flickrResponse: List<PhotoResponse>? = response.body()!!.results.toMutableList()
                var results: MutableList<Girls> = mutableListOf()
                if (flickrResponse != null) {
                    for (i in flickrResponse.indices){
                        results += (flickrResponse[i].images)
                    }
                }
                results = results.filterNot {
                    it.image.isBlank()
                } as MutableList<Girls>

                Log.d(TAG, "results")

                responseLiveData.value = results
            }
        })
        return responseLiveData
    }


    @WorkerThread
    fun fetchPhoto(url: String): Bitmap?{
        val response: Response<ResponseBody> = flickrApi.fetchUrlBytes(url).execute()
        val bitmap = response.body()?.byteStream()?.use(BitmapFactory::decodeStream)
        Log.i(TAG, "Decoded bitmap=$bitmap from Response=$response")
        return bitmap
    }
}