package com.example.github_android.database

import com.example.github_android.database.entitys.Repository

object Database {

    private var repositorys: MutableList<Repository> = mutableListOf()

    fun getRepositorys() = repositorys

    fun setRepositorys(list: List<Repository>) {
        repositorys = list.toMutableList()
    }

    fun addRepostorys(list: List<Repository>) {
        for (repository in list) {
            repositorys.add(repositorys.count(), repository)
        }
    }
}