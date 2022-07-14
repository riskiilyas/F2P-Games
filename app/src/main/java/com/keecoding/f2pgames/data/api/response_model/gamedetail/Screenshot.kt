package com.keecoding.f2pgames.data.api.response_model.gamedetail


import com.google.gson.annotations.SerializedName

data class Screenshot(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String
)