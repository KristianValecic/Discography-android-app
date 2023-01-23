package hr.valecic.discographyapp.model

import hr.valecic.discographyapp.api.ImageItem

data class Artist(
    var _id: Long?,
    val name: String,
    //val image : List<ImageItem>,
    val streamable : Boolean,
    val match : String,
    var favorite: Boolean
)
