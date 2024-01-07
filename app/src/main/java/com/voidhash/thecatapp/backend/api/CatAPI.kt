package com.voidhash.thecatapp.backend.api

import com.voidhash.thecatapp.backend.models.Kitty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CatAPI {
    @GET("breeds")
    fun getBreeds(): Call<List<Kitty>>

    @Headers("x-api-key: live_cn14HGaIWT20VxATZGu8nzBPv6EOQ1bj7KXnjTdn4PouQfech5ZXKaPjUiaSzqCS")
    @GET("breeds/search")
    fun getKitty(@Query("q") breedCode: String): Call<List<Kitty>>
}
