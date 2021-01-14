package com.darkabhi.randomapi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darkabhi.randomapi.data.api.ComicDBInterface
import com.darkabhi.randomapi.vo.ComicDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Abhishek AN on 1/14/2021.
 */
class ComicDetailsNetworkDataSource(
    private val apiService: ComicDBInterface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedComicDetailsResponse = MutableLiveData<ComicDetails>()
    val downloadedComicDetailsResponse: LiveData<ComicDetails>
        get() = _downloadedComicDetailsResponse

    fun fetchComicDetails() {
        _networkState.postValue(NetworkState.LOADING)
        try {
            compositeDisposable.add(
                apiService.getComicDetails()
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedComicDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("fetchComicDetails: ", it.message.toString())
                        })
            )
        } catch (e: Exception) {
            Log.e("fetchComicDetails: ", e.message.toString())
        }
    }
}

