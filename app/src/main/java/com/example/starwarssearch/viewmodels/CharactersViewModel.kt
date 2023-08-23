package com.example.starwarssearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.repository.ItemsRepository
import com.example.starwarssearch.presentation.mapper.CharacterMapper
import com.example.starwarssearch.presentation.models.CharacterPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repository: ItemsRepository,
    val mapper: CharacterMapper
) : ViewModel() {
    fun getCharacters(searchString: String): Flow<PagingData<CharacterPresentation>>? {
        var result: Flow<PagingData<CharacterPresentation>>? = null
        viewModelScope.launch {
            try {
                result = repository.getCharacters(searchString).map {
                    it.map(mapper::map)
                }
            } catch (e: Exception) {

            }
        }
        return result
    }



}