package com.darkabhi.randomapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.darkabhi.randomapi.repository.NetworkState
import com.darkabhi.randomapi.vo.ComicDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Created by Abhishek AN on 1/14/2021.
 */
class SingleComicViewModel(private val comicDetailRepository: ComicDetailRepository): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val comicDetails : LiveData<ComicDetails> by lazy {
        comicDetailRepository.fetchComic(compositeDisposable)
    }
    val networkState:LiveData<NetworkState> by lazy {
        comicDetailRepository.getComicDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}