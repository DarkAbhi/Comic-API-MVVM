package com.darkabhi.randomapi.data.api

import com.darkabhi.randomapi.vo.ComicDetails
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Created by Abhishek AN on 1/14/2021.
 */
interface ComicDBInterface {

    // https://xkcd.com/614/info.0.json

    @GET("614/info.0.json")
    fun getComicDetails(): Single<ComicDetails>
}