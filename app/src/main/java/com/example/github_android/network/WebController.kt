package com.example.github_android.network

import com.example.github_android.database.entitys.Repositorys
import com.example.github_android.network.api.GithubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WebController {

    private val api = GithubAPI().requests()

    fun getRepository(language: String, sort: String, response: (Repositorys) -> Unit) {
        api.getNowPlaying("language:$language", sort, 1).enqueue(requestResponse<Repositorys>(response))
    }

    fun getRepository(language: String, sort: String, pag: Int, response: (Repositorys) -> Unit) {
        api.getNowPlaying("language:$language", sort, pag).enqueue(requestResponse<Repositorys>(response))
    }

    private fun <T> requestResponse(funResponse: (body: T) -> Unit) = object : Callback<T> {
        override fun onFailure(call: Call<T?>, t: Throwable) = Unit

        override fun onResponse(call: Call<T?>, response: Response<T?>) {
            response.body()?.apply(funResponse)
        }
    }

}