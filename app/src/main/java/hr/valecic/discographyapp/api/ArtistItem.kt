package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class ArtistItem(
    @SerializedName("name") val name : String,
    @SerializedName("mbid") val mbid : String,
    @SerializedName("match") val match : Int,//koliko je slican odabranom artistu
    @SerializedName("url") val url : String,
    @SerializedName("image") val image : List<ImageItem>,
    @SerializedName("streamable") val streamable : Int,
    //info method gets these items
    @SerializedName("ontour") val ontour : Int,
    //@SerializedName("stats") val stats : Stats,
    //@SerializedName("similar") val similar : Similar,
    @SerializedName("tags") val tags : TagItem,
    @SerializedName("bio") val bio : BioItem
)
