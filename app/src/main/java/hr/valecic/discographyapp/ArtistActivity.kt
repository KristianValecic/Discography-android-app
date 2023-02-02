package hr.valecic.discographyapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import hr.valecic.discographyapp.adapter.AlbumAdapter
import hr.valecic.discographyapp.adapter.TagAdapter
import hr.valecic.discographyapp.databinding.ActivityArtistBinding
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.model.Album
import hr.valecic.discographyapp.model.Artist
import kotlinx.coroutines.sync.Mutex


const val POSITION = "hr.valecic.discographyapp.position"

class ArtistActivity() : AppCompatActivity() {
    companion object {
        var position = 0
        lateinit var artist: Artist
        lateinit var albums: List<Album>
    }

    private lateinit var binding: ActivityArtistBinding

    private lateinit var tvArtistName: TextView
    private lateinit var tvListeners: TextView
    private lateinit var tvPlaycount: TextView
    private lateinit var tvBio: TextView
    private lateinit var ivFavorite: ImageView
    private lateinit var tagAdapter: TagAdapter
    private lateinit var albumAdapter: AlbumAdapter
    private var tagList = ArrayList<String>()
    private var albumList = ArrayList<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayoutComponents()
        bind(artist)
    }

    private fun initLayoutComponents() {
        tvArtistName = findViewById(R.id.tvArtistName)
        tvListeners = findViewById(R.id.tvListeners)
        tvPlaycount = findViewById(R.id.tvPlayercount)
        tvBio = findViewById(R.id.tvBio)
        ivFavorite = findViewById(R.id.ivFavorite)
    }


    fun bind(artist: Artist) {
        tvArtistName.text = artist.name
        tvListeners.text = "${tvListeners.text}  ${artist.listeners}"
        tvPlaycount.text = "${tvPlaycount.text}  ${artist.playcount}"
        tvBio.text = "${artist.bio?.summary?.trim()}"
        initFavoriteStar()
        fillTags()
        fillAlbums()
    }

    private fun initFavoriteStar() {
        if(FirebaseAuth.getInstance().currentUser == null){
            ivFavorite.visibility = View.GONE
        }else  {
            ivFavorite.setImageResource(if (artist.favorite) R.drawable.star_fill else R.drawable.star)
            ivFavorite.tag = if (artist.favorite) R.drawable.star_fill else R.drawable.star
            ivFavorite.setOnClickListener() {
                updateItem()
                if (ivFavorite.tag == R.drawable.star_fill) {
                    ivFavorite.setImageResource(R.drawable.star)
                    ivFavorite.tag = R.drawable.star
                    Toast.makeText(this,getString(R.string.removed_from_fav), Toast.LENGTH_SHORT).show()
                } else {
                    ivFavorite.setImageResource(R.drawable.star_fill)
                    ivFavorite.tag = R.drawable.star_fill
                    Toast.makeText(this, getString(R.string.added_to_fav), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateItem() {
        val item = fetchItems().find { it.name == artist.name }
        item!!.favorite = !item!!.favorite
        contentResolver.update(
            ContentUris.withAppendedId(DISCOG_PROVIDER_CONTENT_URI, item._id!!),
            ContentValues().apply {
                put(Artist::favorite.name, item.favorite)
            },
            null,
            null
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun fillAlbums() {
        albumAdapter = AlbumAdapter(albumList)
        binding.albumsContainer.apply {
            layoutManager = LinearLayoutManager(this@ArtistActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = albumAdapter
        }
        albums.forEach {
            albumList.add(it)
            albumAdapter.notifyItemInserted(albumList.size - 1)
        }
    }

    private fun fillTags() {
        tagAdapter = TagAdapter(tagList)
        binding.tagsContainer.apply {
            layoutManager =
                LinearLayoutManager(this@ArtistActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = tagAdapter
        }
        artist.tags?.tags?.forEach {
            tagList.add(it.name)
            tagAdapter.notifyItemInserted(tagList.size - 1)
        }
    }
}