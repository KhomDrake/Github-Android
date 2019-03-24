package com.example.github_android.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github_android.R
import com.example.github_android.database.entitys.Owner
import com.example.github_android.database.entitys.Repository

class RecyclerViewAdapterRepositorys (
    private val repositoryInformation: MutableList<Repository>
) : RecyclerView.Adapter<RecyclerViewAdapterRepositorys.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositoryInformation[position])
    }

    fun addRepositoryInformation(list: List<Repository>) {
        removeLoading()
        for (repository in list) {
            repositoryInformation.add(repositoryInformation.count(), repository)
        }
        notifyDataSetChanged()
    }

    fun resetRepositoryInformation() {
        repositoryInformation.removeAll(repositoryInformation)
    }

    fun hasLoading() : Boolean {
        for (repository in repositoryInformation) {
            if(repository.load)
                return true
        }
        return false
    }

    fun addRepositoryInformation(item: Repository) {
        repositoryInformation.add(repositoryInformation.count(), item)
        notifyDataSetChanged()
    }

    fun addLoading() {
        repositoryInformation.add(repositoryInformation.count(), Repository(0,
            "",
            0,
            0,
            Owner(""),
            true)
        )
        notifyDataSetChanged()
    }

    private fun removeLoading() {
        for (repository in repositoryInformation) {
            if(repository.load) {
                repositoryInformation.remove(repository)
            }
        }
    }

    override fun getItemCount() = repositoryInformation.count()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stars: TextView = itemView.findViewById(R.id.stars)
        val nomes: TextView = itemView.findViewById(R.id.nome)
        val forks: TextView = itemView.findViewById(R.id.fork)
        val watchers: TextView = itemView.findViewById(R.id.watchers)
        val img: ImageView = itemView.findViewById(R.id.imageGithub)
        
        fun bind(repository: Repository) {
            if(repository.load) {
                stars.visibility = View.INVISIBLE
                nomes.visibility = View.INVISIBLE
                forks.visibility = View.INVISIBLE
                watchers.visibility = View.INVISIBLE
                Glide.with(itemView)
                    .load("https://upload.wikimedia.org/wikipedia/commons/3/3a/Gray_circles_rotate.gif").into(img)
                return
            }
            stars.visibility = View.VISIBLE
            nomes.visibility = View.VISIBLE
            forks.visibility = View.VISIBLE
            watchers.visibility = View.VISIBLE
            val textFullName = "Nome: ${repository.fullName}"
            val textStars = "Stars: ${repository.stargazersCount}"
            val textForks = "Forks: ${repository.forksCount}"
            val textWatchers = "Watchers: ${repository.watchersCount}"
            nomes.text = textFullName
            stars.text = textStars
            forks.text = textForks
            watchers.text = textWatchers
            Glide.with(itemView).load(repository.owner.avatarUrl).into(img)
        }
    }
}
