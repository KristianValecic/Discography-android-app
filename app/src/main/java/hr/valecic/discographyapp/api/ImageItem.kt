package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("#text") val text : String,
    @SerializedName("size") val size : String
    )
