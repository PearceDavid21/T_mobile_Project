package com.example.dap_tmobile.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.dap_tmobile.Model.Item
import com.example.dap_tmobile.Model.UserInfo
import com.example.dap_tmobile.Network.Githubapi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    val userToAdd = MutableLiveData<UserInfo>()
    val clearList = MutableLiveData<Boolean>()
    private val cacheFile = application.cacheDir


    fun onUserNameTextChanged(currentInput: CharSequence,start: Int,before : Int,
                              count :Int){
        val currentUserNameEntered = currentInput.toString()
        clearList.postValue(true)
        findUsersInfo(currentUserNameEntered)
    }

    private fun findUsersInfo(userName: String) {
        compositeDisposable.add(
            getUserSearchObservable(userName)
                .subscribeOn(Schedulers.io())
                .flatMap { item -> getUserInfo(item)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    private fun getUserSearchObservable(userName: String) =
        Githubapi
            .gitGitHubApiService(cacheFile)
            .getUserSearchResults(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { users -> Observable.fromIterable(users.items).take(10) }

    private fun getUserInfo(item: Item) =
        Githubapi
            .gitGitHubApiService(cacheFile)
            .getUserInformation(item.login)
            .map { user -> userToAdd.postValue(user) }
            .subscribeOn(Schedulers.io())
}