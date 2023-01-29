package hr.valecic.discographyapp.model

import com.google.gson.annotations.SerializedName
import hr.valecic.discographyapp.api.ArtistItem
import hr.valecic.discographyapp.api.ImageItem

data class Album (
    var _id: Long?,
    var name : String?,
    var playcount : Long?,
    var images : List<Image>?
)