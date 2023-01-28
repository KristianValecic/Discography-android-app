package hr.valecic.discographyapp.api

import retrofit2.Call
import retrofit2.http.GET

const val KEY = "fd62d8a51e7d0928197a212a9831a537"
const val ARTIST = "fugazi"
const val API_METHOD_GET_SIMILAR = "artist.getsimilar&artist="
const val API_BASE = "https://ws.audioscrobbler.com/2.0/"
const val API_METHOD_PREFIX = "?method="

interface DiscogApi {
    //https://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=debeli%20precjednik&api_key=fd62d8a51e7d0928197a212a9831a537&format=json
    //format: base + method + item + key + format
    @GET(API_METHOD_PREFIX + API_METHOD_GET_SIMILAR + ARTIST + "&api_key=" + KEY + "&format=json")
    fun fetchItems(): Call<SimilarArtistsItem>
}