package com.voidhash.thecatapp.backend.api

import com.voidhash.thecatapp.backend.models.Kitty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatAPI {
    @GET("breeds")
    fun getBreeds(): Call<List<Kitty>>

    @GET("breeds/search")
    fun getKitty(@Query("q") breedCode: String): Call<List<Kitty>>
}
