package com.twitty.assignment.data.source.network.model

import com.google.gson.annotations.SerializedName

data class NetworkBook(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("discount")
    val price: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("pubdate")
    val publicationDate: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("description")
    val description: String,
)
