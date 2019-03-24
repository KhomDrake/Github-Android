package com.example.github_android.database.entitys

import com.google.gson.annotations.SerializedName

data class Repositorys(
    val items: List<Repository>,
    @SerializedName("total_count")
    val totalCount: Int
)