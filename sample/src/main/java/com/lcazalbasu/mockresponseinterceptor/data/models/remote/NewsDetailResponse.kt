package com.lcazalbasu.mockresponseinterceptor.data.models.remote

import com.google.gson.annotations.SerializedName

data class NewsDetailResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
)
