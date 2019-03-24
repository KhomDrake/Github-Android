package com.example.github_android.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubAPI {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/search/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var default: ApiRequest? = null

    fun requests(): ApiRequest {
        if (default == null) {
            default = retrofit.create(ApiRequest::class.java)
        }

        return default!!
    }
}