package com.example.starwarssearch.data.models

import com.google.gson.annotations.SerializedName

data class HomeWorldResponse(
    @SerializedName("name")
    val name: String
)