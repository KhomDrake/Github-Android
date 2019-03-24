package com.example.github_android

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github_android.ui.MainActivityViewController
import com.example.github_android.ui.RecyclerViewAdapterRepositorys

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var mainActivityViewController: MainActivityViewController
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewController = MainActivityViewController()

        spinner = findViewById(R.id.linguagem)
        spinner.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            mainActivityViewController.getListOfLanguages()
        )

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.repositorys)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RecyclerViewAdapterRepositorys(mutableListOf())

        spinner.selectItem()
        recyclerView.loadFirstRepository()
        recyclerView.scrollInfinite()
    }

    private fun RecyclerView.loadFirstRepository() {
        if (mainActivityViewController.isFirstLoad())
            this.repositoryAdapter.addLoading()

        mainActivityViewController.getFirstRepositorys {
            this.repositoryAdapter.addRepositoryInformation(it)
        }
    }

    private fun RecyclerView.loadMoreRepository() {
        this.repositoryAdapter.addLoading()
        mainActivityViewController.getRepositorysScroll {
            this.repositoryAdapter.addRepositoryInformation(it)
        }
    }

    private fun RecyclerView.scrollInfinite() {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                if (!recyclerView.repositoryAdapter.hasLoading() && totalItemCount == lastVisibleItemPosition + 1) {
                    recyclerView.loadMoreRepository()
                }
            }
        })
    }

    private val RecyclerView.repositoryAdapter: RecyclerViewAdapterRepositorys
        get() = adapter as RecyclerViewAdapterRepositorys

    private fun Spinner.selectItem() {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) = Unit

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (!mainActivityViewController.validSelection(p2))
                    return
                recyclerView.repositoryAdapter.resetRepositoryInformation()
                recyclerView.repositoryAdapter.addLoading()
                mainActivityViewController.getRepositorysSpinner(p2) {
                    recyclerView.repositoryAdapter.addRepositoryInformation(it)
                }
            }
        }
    }
}
