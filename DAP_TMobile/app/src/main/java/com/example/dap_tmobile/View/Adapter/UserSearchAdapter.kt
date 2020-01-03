package com.example.dap_tmobile.View.Adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dap_tmobile.Model.UserInfo
import com.example.dap_tmobile.R
import com.example.dap_tmobile.View.RepoActivity
import com.tobysoft.originhub.R
import kotlinx.android.synthetic.main.user_search_item.view.*
import kotlinx.android.synthetic.main.user_search_obj.view.*

class UserSearchAdapter()
    : RecyclerView.Adapter<UserSearchAdapter.ViewHolder>() {
    private var userInformationList = ArrayList<UserInfo>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.user_search_item, parent, false))

    override fun getItemCount() = userInformationList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bindUserInfo(userInformationList[position])

    fun updateUserInformationList(userInformation : UserInfo) {
        userInformationList.add(userInformation)
        notifyDataSetChanged()
    }

    fun clearUserInformationList() {
        userInformationList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindUserInfo(userInformation : UserInfo){
            itemView.UserName.text = userInformation.login
            itemView.ReposCount.text = "Repo: ${userInformation.public_repos}"
            Glide
                .with(itemView)
                .load(userInformation.avatar_url)
                .into(itemView.imgUserAvatar)
            itemView.setOnClickListener {
                val intent = Intent(it.context, RepoActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(USER_INFO_ID, userInformation)
                intent.putExtras(bundle)
                it.context.startActivity(intent)
            }
        }
    }
}