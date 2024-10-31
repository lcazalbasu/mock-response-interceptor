package com.lcazalbasu.mockresponseinterceptor.data.models.remote

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("thumb")
    val thumbUrl: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
)
