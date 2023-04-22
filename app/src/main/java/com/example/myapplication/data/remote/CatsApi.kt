package com.example.myapplication.data.remote

import retrofit2.http.GET

interface CatsApi {

    @GET("/v1/breeds/")
    suspend fun fetchCats(): List<CatsResponse>
}
