package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class TopAlbumsItem (
    @SerializedName("topalbums") val topalbums : TopAlbumsItem,
    @SerializedName("album") val albums : List<AlbumItem>
)