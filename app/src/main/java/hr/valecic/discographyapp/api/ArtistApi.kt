package hr.valecic.discographyapp.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

//var ARTIST
//const val KEY = "fd62d8a51e7d0928197a212a9831a537"
const val API_METHOD_GET_INFO = "artist.getinfo&artist="/*&artist=*/
const val API_SUFFIX = "&api_key=$KEY&format=json"
//const val API_BASE = "https://ws.audioscrobbler.com/2.0/"
//const val API_METHOD_PREFIX = "?method="

interface ArtistApi {
    //https://ws.audioscrobbler.com/2.0/?method=artist.getsimilar&artist=debeli%20precjednik&api_key=fd62d8a51e7d0928197a212a9831a537&format=json
    //format: base + method + item + key + format
    @GET(/*"https://ws.audioscrobbler.com/2.0/?method=artist.getinfo&artist={artistName}}&api_key=fd62d8a51e7d0928197a212a9831a537&format=json"*/)
    fun fetchInfo(@Url url: String): Call<ArtistItem>
}