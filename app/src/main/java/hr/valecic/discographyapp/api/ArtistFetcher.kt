package hr.valecic.discographyapp.api

import android.content.Context
import android.util.Log
import hr.valecic.discographyapp.ArtistActivity
import hr.valecic.discographyapp.ArtistReceiver
import hr.valecic.discographyapp.DISCOG_PROVIDER_CONTENT_URI
import hr.valecic.discographyapp.DiscogReceiver
import hr.valecic.discographyapp.framework.sendBroadcast
import hr.valecic.discographyapp.model.Album
import hr.valecic.discographyapp.model.Artist
import hr.valecic.discographyapp.model.Image
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistFetcher(private val context: Context) {
    private lateinit var artistApi: ArtistApi

    companion object {
        private var isArtistInfoFetched = false
        private var areAlbumsFetched = false
    }

    fun fetchInfo(name: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        artistApi = retrofit.create(ArtistApi::class.java)

        var sb = StringBuilder()
        sb.append(API_BASE).append(API_METHOD_PREFIX).append("artist.getinfo&artist=").append(name)
            .append(API_SUFFIX)

        val request = artistApi.fetchInfo(sb.toString())

        request.enqueue(object : Callback<ArtistItem> {
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
            ArtistActivity.artist = Artist(
                null,
                artist.name, /*mutableListOf(),*/
                artist.streamable == 1,
                null,
                false,
                artist.stats.listeners?.toLong(),
                artist.stats.playcount?.toLong(),
                artist.tags,
                artist.bio
            )
        }
        isArtistInfoFetched = true;
        sendBroadcast()
    }

    fun fetchAlbums(name: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        artistApi = retrofit.create(ArtistApi::class.java)
//            https://ws.audioscrobbler.com/2.0/?method=artist.gettopalbums&artist=cher&api_key=fd62d8a51e7d0928197a212a9831a537&format=json
        var sb = StringBuilder()
        sb.append(API_BASE).append(API_METHOD_PREFIX).append("artist.gettopalbums&artist=")
            .append(name).append(API_SUFFIX)

        val request = artistApi.fetchAlbums(sb.toString())

        request.enqueue(object : Callback<TopAlbumsItem> {
            override fun onResponse(
                call: Call<TopAlbumsItem>,
                response: Response<TopAlbumsItem>
            ) {
                response.body()?.let { populateAlbumItem(it) }
            }

            override fun onFailure(call: Call<TopAlbumsItem>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }
        })
    }

    private fun populateAlbumItem(topAlbums: TopAlbumsItem) {
//        var album = artistWrapper.artist
        var fetchedAlbums = mutableListOf<Album>()
        GlobalScope.launch {
            topAlbums.topalbums.albums.forEach {
                var fetchedAlbumImages = mutableListOf<Image>()
                it.images.forEach {
                    if (!it.text.isEmpty() && !it.size.isEmpty() && it.size == "large") {
                        fetchedAlbumImages.add(Image(it.text, it.size))
                    }
                }
                if (it.name.isEmpty() != true) {
                    fetchedAlbums.add(Album(null, it.name, it.playcount, fetchedAlbumImages))
                }
            }
            ArtistActivity.albums = fetchedAlbums
        }
        areAlbumsFetched = true
        sendBroadcast()
    }

    private fun sendBroadcast() {
        if (isArtistInfoFetched && areAlbumsFetched) {
            isArtistInfoFetched = false
            areAlbumsFetched = false
            context.sendBroadcast<ArtistReceiver>()
        }
    }
}

