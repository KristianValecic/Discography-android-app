package hr.valecic.discographyapp.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.valecic.discographyapp.ArtistActivity
import hr.valecic.discographyapp.ArtistReceiver
import hr.valecic.discographyapp.DISCOG_PROVIDER_CONTENT_URI
import hr.valecic.discographyapp.DiscogReceiver
import hr.valecic.discographyapp.framework.sendBroadcast
import hr.valecic.discographyapp.model.Artist
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ArtistFetcher(private val context: Context)  {
    private lateinit var artistApi: ArtistApi
//    make endpoint url
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(API_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    artistApi = retrofit.create(ArtistApi::class.java)
//    }

    fun fetchInfo(name: String){
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        artistApi = retrofit.create(ArtistApi::class.java)

        var sb = StringBuilder()
        sb.append(API_BASE).append(API_METHOD_PREFIX).append("artist.getinfo&artist=").append(name).append(API_SUFFIX)

        val request = artistApi.fetchInfo(sb.toString())

        request.enqueue(object: Callback<ArtistItem> {
            override fun onResponse(
                call: Call<ArtistItem>,
                response: Response<ArtistItem>
            ) {
                response.body()?.let { populateItem(it) }
            }

            override fun onFailure(call: Call<ArtistItem>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }
        })
    }

    private fun populateItem(artistWrapper: ArtistItem) {
//        println(artist)
        var artist = artistWrapper.artist
        var fetchedArtist: Artist
        GlobalScope.launch {
            ArtistActivity.artist = Artist(null, artist.name, /*mutableListOf(),*/ artist.streamable == 1, null,
                false, artist.stats.listeners?.toLong(), artist.stats.playcount?.toLong() ,artist.tags.tags, artist.bio)

//                val values = ContentValues().apply {
//                    put(Artist::name.name, it.name)
//                    put(Artist::match.name, it.match)
//                    put(Artist::streamable.name, it.streamable)
//                    put(Artist::favorite.name, false)
//                    //put(Artist::streamable.name, it.streamable)
//                }
//                context.contentResolver.insert(DISCOG_PROVIDER_CONTENT_URI, values)
            }
//        ArtistActivity.progress.dismiss()
        context.sendBroadcast<ArtistReceiver>()
    }
//    }
}

