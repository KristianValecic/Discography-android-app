package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName
import hr.valecic.discographyapp.model.Artist

data class ArtistItem(

    @SerializedName("artist") val artist: ArtistItem,
    @SerializedName("name") val name : String,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("match") val match : String,
    @SerializedName("url") val url : String,
    @SerializedName("image") val image : List<ImageItem>,
    @SerializedName("streamable") val streamable : Int,

    //info method gets these items
    @SerializedName("ontour") val ontour : Int,
    @SerializedName("stats") val stats : StatsItem,
    @SerializedName("tags") val tags : TagItem,
    @SerializedName("bio") val bio : BioItem
)
