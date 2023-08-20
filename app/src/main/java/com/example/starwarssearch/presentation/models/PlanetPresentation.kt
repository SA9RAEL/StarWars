package com.example.starwarssearch.presentation.models

import com.example.starwarssearch.data.models.Character
import com.example.starwarssearch.presentation.ItemListPresentation
import com.google.gson.annotations.SerializedName

data class PlanetPresentation(
    override val id: Int,
    val climate: String,
    val created: String,
    val diameter: String,
    val edited: String,
    val films: List<String>,
    val gravity: String,
    val name: String,
    val orbitalPeriod: String,
    val population: String,
    val residents: List<Any>,
    val rotationPeriod: String,
    val surfaceWater: String,
    val terrain: String,
    val url: String
) : ItemListPresentation
