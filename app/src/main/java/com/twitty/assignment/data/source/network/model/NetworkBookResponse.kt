package com.twitty.assignment.data.source.network.model

import com.google.gson.annotations.SerializedName

data class NetworkBookResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    @SerializedName("items")
    val books:List<NetworkBook>
)

val emptyNetworkBookResponse = NetworkBookResponse(
    lastBuildDate = "",
    total = 0,
    start = 0,
    display = 0,
    books = emptyList()
)
