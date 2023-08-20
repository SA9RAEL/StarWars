package com.example.starwarssearch.data.models

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    @SerializedName("title")
    val title: String
)