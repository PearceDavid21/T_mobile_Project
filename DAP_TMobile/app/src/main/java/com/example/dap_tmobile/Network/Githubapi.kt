package com.example.dap_tmobile.Network

import com.example.dap_tmobile.Model.UserInfo
import com.example.dap_tmobile.Model.UserRepo
import com.example.dap_tmobile.Model.UserSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File

interface Githubapi {
    companion object{
        fun gitGitHubApiService(cacheFile : File) =
            RetrofitHelper.getRetrofitInstance(GITHUB_BASE_URL, cacheFile).create(Githubapi::class.java)
    }

    @GET("/search/users")
    fun getUserSearchResults(@Query("q")userName: String): Observable<UserSearch>

    @GET("/users/{user_name}")
    fun getUserInformation(@Path("user_name")userName: String) : Observable<UserInfo>

    @GET("/users/{user_name}/repos")
    fun getRepositories(@Path("user_name") userName: String): Observable<List<UserRepo>>
}