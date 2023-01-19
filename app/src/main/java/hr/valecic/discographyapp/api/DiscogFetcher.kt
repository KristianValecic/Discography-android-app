package hr.valecic.discographyapp.api

import android.content.Context
import android.util.Log
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

class DiscogFetcher(private val context: Context) {
    private var discogApi: DiscogApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        discogApi = retrofit.create(DiscogApi::class.java)
    }

    fun fetchItems(){
        val request = discogApi.fetchItems()

        request.enqueue(object: Callback<SimilarArtistsItem>{
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
//        println(artistItems)
        val fetchedArtists = mutableListOf<Artist>()
        GlobalScope.launch {
            artistItems.artistsList?.forEach{
                fetchedArtists.add(Artist(null, it.name, it.image, it.streamable, false))
            }
        }
        context.sendBroadcast<DiscogReceiver>()
    }
}

