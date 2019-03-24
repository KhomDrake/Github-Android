package com.example.github_android.database.entitys

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    val owner: Owner,
    val load: Boolean
)