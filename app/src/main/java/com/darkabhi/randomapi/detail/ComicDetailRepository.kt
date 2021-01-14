package com.darkabhi.randomapi.detail

import androidx.lifecycle.LiveData
import com.darkabhi.randomapi.data.api.ComicDBInterface
import com.darkabhi.randomapi.repository.ComicDetailsNetworkDataSource
import com.darkabhi.randomapi.repository.NetworkState
import com.darkabhi.randomapi.vo.ComicDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Abhishek AN on 1/14/2021.
 */
class ComicDetailRepository(private val apiService: ComicDBInterface) {
    lateinit var comicDetailsNetworkDataSource: ComicDetailsNetworkDataSource

    fun fetchComic(compositeDisposable: CompositeDisposable) : LiveData<ComicDetails> {
        comicDetailsNetworkDataSource = ComicDetailsNetworkDataSource(apiService,compositeDisposable)
        comicDetailsNetworkDataSource.fetchComicDetails()
        return comicDetailsNetworkDataSource.downloadedComicDetailsResponse
    }

    fun getComicDetailsNetworkState():LiveData<NetworkState>{
        return comicDetailsNetworkDataSource.networkState
    }
}