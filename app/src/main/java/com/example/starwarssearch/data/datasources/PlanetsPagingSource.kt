package com.example.starwarssearch.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarssearch.data.models.Planet
import com.example.starwarssearch.network.ApiService
import com.example.starwarssearch.utils.Constants

class PlanetsPagingSource(private val apiService: ApiService, private val searchString: String) :
    PagingSource<Int, Planet>() {

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        val position = params.key ?: Constants.FIRST_PAGE_INDEX
        return try {
            val response = apiService.getPlanets(position)
            val planets = response.results

            val filteredData = planets.filter { it.name.contains(searchString, true) }

            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
