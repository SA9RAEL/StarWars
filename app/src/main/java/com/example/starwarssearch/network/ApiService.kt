package com.example.starwarssearch.network

import com.example.starwarssearch.data.models.FilmsResponse
import com.example.starwarssearch.data.models.HomeWorldResponse
import com.example.starwarssearch.data.models.PeopleResponse
import com.example.starwarssearch.data.models.PlanetsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/?page/")
    suspend fun getCharacters(@Query("page") page: Int): PeopleResponse

    @GET("planets/?page/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetsResponse

    @GET
    suspend fun getFilms(@Url url: String): FilmsResponse

    @GET
    suspend fun getHomeWorld(@Url url: String): HomeWorldResponse
}