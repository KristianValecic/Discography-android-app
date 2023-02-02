package hr.valecic.discographyapp.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.valecic.discographyapp.ArtistService
import hr.valecic.discographyapp.DiscogReceiver
import hr.valecic.discographyapp.DISCOG_PROVIDER_CONTENT_URI
import hr.valecic.discographyapp.framework.sendBroadcast
import hr.valecic.discographyapp.model.Artist
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscogFetcher(private val context: Context) {
    private var discogApi: DiscogApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        discogApi = retrofit.create(DiscogApi::class.java)
    }

    fun fetchItems() {
        val request = discogApi.fetchItems()

        request.enqueue(object : Callback<SimilarArtistsItem> {
            override fun onResponse(
                call: Call<SimilarArtistsItem>,
                response: Response<SimilarArtistsItem>
            ) {
                response.body()?.let { populateItems(it) }
            }

            override fun onFailure(call: Call<SimilarArtistsItem>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }
        })
    }

    private fun populateItems(artistItems: SimilarArtistsItem) {
        println(artistItems)
        val fetchedArtists = mutableListOf<Artist>()
        GlobalScope.launch {
            artistItems.similarartists.artistsList?.forEach {
                    fetchedArtists.add(
                        Artist(
                            null, it.name, /*mutableListOf(),*/ it.streamable == 1, it.match,
                            false, null, null, null, null
                        )
                    )

                    val values = ContentValues().apply {
                        put(Artist::name.name, it.name)
                        put(Artist::match.name, it.match)
                        put(Artist::streamable.name, it.streamable)
                        put(Artist::favorite.name, false)
                        //put(Artist::streamable.name, it.streamable)
                    }
                    context.contentResolver.insert(DISCOG_PROVIDER_CONTENT_URI, values)
//                }
            }
        }
        context.sendBroadcast<DiscogReceiver>()
    }
}

