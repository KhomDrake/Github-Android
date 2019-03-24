package com.example.github_android.database

import android.provider.ContactsContract
import com.example.github_android.database.entitys.Repository
import com.example.github_android.database.entitys.Repositorys
import com.example.github_android.network.WebController
import kotlin.math.max

object DataController {

    private val listOfLanguages = arrayOf("kotlin", "java", "csharp", "javascript", "python")
    private var pag: Int = 1
    private val maxPag: Int = 34

    fun getListOfLanguages() = listOfLanguages

    fun getLanguage(i: Int) = listOfLanguages[i]

    var language: String = ""

    fun getPag() = pag

    fun addPag() {
       pag++
       if(pag > maxPag)
           pag = maxPag
    }

    fun resetPag() {
        pag = 1
    }

    fun getRepositoryScroll(response: (List<Repository>) -> Unit) {
        if(pag == maxPag)
            return
        addPag()
        WebController.getRepository(language, "stars", pag) {
            Database.addRepostorys(it.items)
            response(it.items)
        }
    }

    fun getFirstRepositorys(response: (List<Repository>) -> Unit) {
        if(!isFirstLoad()) {
            response(Database.getRepositorys())
        } else {
            language = getLanguage(0)
            WebController.getRepository(language, "stars") {
                Database.setRepositorys(it.items)
                response(Database.getRepositorys())
            }
        }
    }

    fun getRepositorysSpinner(lang: Int, response: (List<Repository>) -> Unit) {
        if(!validSelection(lang)) {
            response(Database.getRepositorys())
        } else {
            resetPag()
            language = getLanguage(lang)
            WebController.getRepository(language, "stars") {
                Database.setRepositorys(it.items)
                response(Database.getRepositorys())
            }
        }
    }

    fun validSelection(lang: Int) = language != "" && getLanguage(lang) != language

    fun isFirstLoad() = language == ""
}