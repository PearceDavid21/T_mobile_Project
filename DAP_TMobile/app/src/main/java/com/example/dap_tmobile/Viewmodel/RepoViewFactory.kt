package com.example.dap_tmobile.Viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dap_tmobile.Model.UserInfo

@Suppress("UNCHECKED_CAST")
class RepoViewFactory (val application: Application, val userInformationResult: UserInfo)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoViewModel(application, userInformationResult) as T
    }

}