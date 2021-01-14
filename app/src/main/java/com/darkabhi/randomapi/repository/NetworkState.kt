package com.darkabhi.randomapi.repository

/**
 * Created by Abhishek AN on 1/14/2021.
 */

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status:Status,val message:String) {
    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS,"Success")
            LOADING = NetworkState(Status.RUNNING,"Running")
            ERROR = NetworkState(Status.FAILED,"An error occurred.")
        }
    }
}