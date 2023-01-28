package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class TagItem(

    @SerializedName("tag" ) var tags : ArrayList<TagItem> = arrayListOf(),
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String

)
