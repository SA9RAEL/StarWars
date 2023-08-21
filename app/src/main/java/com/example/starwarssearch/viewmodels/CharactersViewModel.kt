package com.example.starwarssearch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.data.repository.ItemsRepository
import com.example.starwarssearch.presentation.mapper.CharacterMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val repository: ItemsRepository,
    val mapper: CharacterMapper
) : ViewModel() {
    fun getCharacters(searchString: String): Flow<PagingData<Character>> =
        repository.getCharacters(searchString).cachedIn(viewModelScope)

}