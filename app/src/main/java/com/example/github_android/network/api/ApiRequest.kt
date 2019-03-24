package com.example.github_android.network.api

import com.example.github_android.database.entitys.Repositorys
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("repositories")
    fun getNowPlaying(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") p: Int = 1
    ): Call<Repositorys>

}