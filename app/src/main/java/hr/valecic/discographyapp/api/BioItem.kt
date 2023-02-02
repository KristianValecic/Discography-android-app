package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class BioItem(
    @SerializedName("published") val published : String,
    @SerializedName("summary") val summary : String,
    @SerializedName("content") val content : String
)
