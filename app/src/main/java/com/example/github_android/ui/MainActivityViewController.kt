package com.example.github_android.ui

import com.example.github_android.database.DataController
import com.example.github_android.database.entitys.Repository
import com.example.github_android.database.entitys.Repositorys

class MainActivityViewController {

    fun getListOfLanguages() = DataController.getListOfLanguages()

    fun getFirstRepositorys(response: (List<Repository>) -> Unit) {
        DataController.getFirstRepositorys(response)
    }

    fun getRepositorysScroll(response: (List<Repository>) -> Unit) {
        DataController.getRepositoryScroll(response)
    }

    fun getRepositorysSpinner(language: Int, response: (List<Repository>) -> Unit) {
        DataController.getRepositorysSpinner(language, response)
    }

    fun isFirstLoad() = DataController.isFirstLoad()

    fun validSelection(language: Int) = DataController.validSelection(language)

}