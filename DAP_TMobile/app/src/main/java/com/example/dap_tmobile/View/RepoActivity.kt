package com.example.dap_tmobile.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.dap_tmobile.Model.UserRepo
import com.example.dap_tmobile.R
import com.example.dap_tmobile.View.Adapter.RepoAdapter
import com.example.dap_tmobile.Viewmodel.RepoViewFactory
import com.example.dap_tmobile.Viewmodel.RepoViewModel
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_repo.*
import kotlinx.android.synthetic.main.user_search_obj.imgUserAvatar

class RepoActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityRepositoryBinding
    lateinit var viewModelFactory : RepoViewFactory
    lateinit var viewModel : RepoViewModel
    lateinit var adapter : RepoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_repository)
        viewModelFactory = RepoViewFactory(application, intent.extras?.getParcelable(USER_INFO_ID)!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)
        viewBinding.viewmodel = viewModel
        viewModel.userAvatar.observe(this, Observer {avatar_url ->
            Glide
                .with(this)
                .load(avatar_url)
                .into(imgUserAvatar)
        })
        viewModel.allRepository.observe(this, Observer { repoList -> updateRepositoryList(repoList) })
        viewModel.filteredRepositoryList.observe(this, Observer { repoList -> updateRepositoryList(repoList) })
    }

    private fun updateRepositoryList(userRepoList : List<UserRepo>) {
        if(::adapter.isInitialized.not()) {
            val linearManager = LinearLayoutManager(this)
            adapter = RepoAdapter(userRepoList)
            rvUserRepositoryList.layoutManager = linearManager
            rvUserRepositoryList.adapter = adapter
        } else {
            adapter.updateList(userRepoList)
        }
    }
}
