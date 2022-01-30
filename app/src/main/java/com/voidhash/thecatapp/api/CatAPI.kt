package com.voidhash.thecatapp.api

import com.voidhash.thecatapp.models.Kitty
import retrofit2.Call
import retrofit2.http.GET

interface CatAPI {
    @GET("breeds")
    fun getBreeds(): Call<List<Kitty>>
}
