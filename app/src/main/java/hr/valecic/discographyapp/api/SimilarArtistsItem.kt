package hr.valecic.discographyapp.api

import com.google.gson.annotations.SerializedName

data class SimilarArtistsItem(
    @SerializedName("similarartists") val similarartists : SimilarArtistsItem,
    @SerializedName("artist") val artistsList : List<ArtistItem>?

)
