package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class  AlbumItem (
    @SerializedName("name") val name : String,
    @SerializedName("playcount") val playcount : Long,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("url") val url : String,
    @SerializedName("artist") val artist : ArtistItem,
    @SerializedName("image") val images : List<ImageItem>
)