package com.unam.appredsocialig.network

import com.unam.appredsocialig.data.PostList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getPost(@Url url: String) : Call<PostList>
}