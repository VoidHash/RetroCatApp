package com.voidhash.thecatapp.backend.api

import com.voidhash.thecatapp.backend.models.Kitty
import retrofit2.Call
import retrofit2.http.GET

interface CatAPI {
    @GET("breeds")
    fun getBreeds(): Call<List<Kitty>>
}
