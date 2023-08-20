package com.example.starwarssearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarssearch.data.datasources.CharactersPagingSource
import com.example.starwarssearch.data.datasources.PlanetsPagingSource
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.models.Planet
import com.example.starwarssearch.network.ApiService
import com.example.starwarssearch.network.SafeApiCall
import com.example.starwarssearch.utils.Constants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemsRepository @Inject constructor(private val apiService: ApiService) : SafeApiCall() {
    fun getCharacters(searchString: String): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            CharactersPagingSource(apiService, searchString)
        }
    ).flow

    fun getPlanets(searchString: String): Flow<PagingData<Planet>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PlanetsPagingSource(apiService, searchString)
        }
    ).flow

    suspend fun getFilm(url: String) = safeApiCall { apiService.getFilms(url) }
    suspend fun getHomeWorld(url: String) = safeApiCall { apiService.getHomeWorld(url) }
}