package com.example.rest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

public interface GitHubClient {
    @GET("/api/users/{user}/repos")
    fun reposForUsers(@Path("user") user:String) : Call<List<GitHubRepo>>

//    @GET ("/user/info") userInfo
//
//    @PUT ("/user/info") updateUserInfo()
//
//    @DELETE ("/user") deleteUser()
}