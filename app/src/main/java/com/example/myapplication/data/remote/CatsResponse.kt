package com.example.myapplication.data.remote

import com.google.gson.annotations.SerializedName

data class CatsResponse(
    val id: String,
    @SerializedName("name")
    val breedName: String?,
    val origin: String?,
    @SerializedName("reference_image_id")
    val referenceId: String?
)
