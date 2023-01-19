package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class TagItem(

    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String

)
