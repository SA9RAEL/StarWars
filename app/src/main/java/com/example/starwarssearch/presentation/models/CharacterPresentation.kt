package com.example.starwarssearch.presentation.models

import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.presentation.ItemListPresentation
import com.google.gson.annotations.SerializedName

data class CharacterPresentation(
    override val id: Int,
    val birthYear: String,
    val eyeColor: String,
    val films: List<String>,
    val gender: String,
    val hairColor: String,
    val height: String,
    val homeWorld: String,
    val mass: String,
    val name: String,
    val skinColor: String
) : ItemListPresentation