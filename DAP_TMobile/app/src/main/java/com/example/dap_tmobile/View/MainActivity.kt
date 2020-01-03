package com.example.dap_tmobile.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dap_tmobile.Model.UserInfo
import com.example.dap_tmobile.R
import com.example.dap_tmobile.View.Adapter.UserSearchAdapter
import com.example.dap_tmobile.Viewmodel.UserModel
import com.example.dap_tmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityMainBinding
    lateinit var viewModel : UserModel
    lateinit var userSearchRVAdapter: UserSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(UserModel::class.java)
        viewBinding.viewmodel = viewModel
        viewModel.clearList.observe(this, Observer { needsCleared ->
            if(::userSearchRVAdapter.isInitialized) {
                if(needsCleared) {
                    userSearchRVAdapter.clearUserInformationList()
                }
            }
        })
        viewModel.userToAdd.observe(this, Observer { userInformation -> addUserToList(userInformation) })
    }

    private fun addUserToList(userInformationResult: UserInfo) {
        if(::userSearchRVAdapter.isInitialized.not()) {
            val layoutManager = LinearLayoutManager(this)
            userSearchRVAdapter = UserSearchAdapter()
            UserSearchAdapter.layoutManager = layoutManager
            UserSearchAdapter.adapter = userSearchRVAdapter

        }
        userSearchRVAdapter.updateUserInformationList(userInformationResult)
    }
}
