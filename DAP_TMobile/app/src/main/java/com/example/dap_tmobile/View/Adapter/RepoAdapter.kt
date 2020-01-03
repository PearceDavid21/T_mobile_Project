package com.example.dap_tmobile.View.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dap_tmobile.Model.UserRepo
import com.example.dap_tmobile.R
import kotlinx.android.synthetic.main.repository_obj.view.*

class RepoAdapter(var repositoryList : List<UserRepo>)
    : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.repository_obj, parent, false))

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bindRepository(repositoryList[position])

    fun updateList(updatedList : List<UserRepo>) {
        repositoryList = updatedList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindRepository(repositoryResults: UserRepo) {
            itemView.ReposName.text = repositoryResults.name
            itemView.ReposForks.text = "${repositoryResults.forksCount} Forks"
            itemView.ReposStars.text = "${repositoryResults.stargazersCount} Stars"
        }
    }
}