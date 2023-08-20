package com.example.starwarssearch.data.models

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val results: List<Planet>
)