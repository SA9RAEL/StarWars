package com.example.starwarssearch.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.network.ApiService
import com.example.starwarssearch.utils.Constants.FIRST_PAGE_INDEX

class CharactersPagingSource(private val apiService: ApiService, private val searchString: String) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = apiService.getCharacters(position)
            val characters = response.results

            val filteredData = characters.filter { it.name.contains(searchString, true) }

            val nextKey = position + 1
            val prevKey = if (position == 1) null else position - 1

            LoadResult.Page(data = filteredData, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}