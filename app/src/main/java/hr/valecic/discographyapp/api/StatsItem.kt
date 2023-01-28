package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class StatsItem(
    @SerializedName("listeners") var listeners: String? = null,
    @SerializedName("playcount") var playcount: String? = null
)
