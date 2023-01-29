package hr.valecic.discographyapp.model

import hr.valecic.discographyapp.api.BioItem
import hr.valecic.discographyapp.api.ImageItem
import hr.valecic.discographyapp.api.TagItem

data class Artist(
    var _id: Long?,
    val name: String,
    //val image : List<ImageItem>,
    val streamable : Boolean,
    val match : String?,
    var favorite: Boolean,
    var listeners: Long?,
    var playcount: Long?,
    var tags: TagItem?,
    var bio: BioItem?
)
