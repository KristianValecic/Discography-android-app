package hr.valecic.discographyapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hr.valecic.discographyapp.adapter.TagAdapter
import hr.valecic.discographyapp.databinding.ActivityArtistBinding
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.model.Artist
import kotlinx.coroutines.sync.Mutex


const val POSITION = "hr.valecic.discographyapp.position"

class ArtistActivity() : AppCompatActivity() {
    companion object {
//        fun startBind(){
//
//        }

        //        lateinit var progress: ProgressDialog
        lateinit var artist: Artist
        lateinit var albums: Album
    }

    private lateinit var binding: ActivityArtistBinding

    //    private lateinit var artists: MutableList<Artist>
//    private lateinit var artist: Artist
    private var position = 0

    private lateinit var tvArtistName: TextView
    private lateinit var tvListeners: TextView
    private lateinit var tvPlaycount: TextView
    private lateinit var tvBio: TextView
    private  var tagList = ArrayList<String>()
    private lateinit var tagAdapter: TagAdapter
//    private lateinit var albumsContainer: LinearLayout
//    private lateinit var tagsContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayoutComponents()
        initPosiiton()
//        getArtist()
        bind(artist)
    }

    private fun initLayoutComponents() {
        tvArtistName = findViewById(R.id.tvArtistName)
        tvListeners = findViewById(R.id.tvListeners)
        tvPlaycount = findViewById(R.id.tvPlayercount)
        tvBio = findViewById(R.id.tvBio)
//        albumsContainer = findViewById(R.id.albumsContainer)
//        tagsContainer = findViewById(R.id.tagsContainer)
    }

//    private fun getArtist() {
////        progress = ProgressDialog(this)
////        progress.setTitle("Loading")
////        progress.setMessage("Wait while loading...")
////        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
////        progress.show()
//        ArtistService.enqueueGetArtistInfo(this, fetchItems()[position].name)
//    }

    private fun initPosiiton() {
        position = intent.getIntExtra(POSITION, position)
    }

    fun bind(artist: Artist) {
        //Picasso.get()
        //.load(File(item.picturePath))
        //.error(R.drawable.nasa)
        //.transform(RoundedCornersTransformation(50, 5))
        //.into(ivItem)
        tvArtistName.text = artist.name
        tvListeners.text = "${tvListeners.text}  ${artist.listeners}"
        tvPlaycount.text = "${tvPlaycount.text}  ${artist.playcount}"
        tvBio.text = "${tvBio.text}  ${artist.bio?.summary}"
//        tagsContainer.background = getDrawable(R.drawable.tag_background)
        fillTags()
    }

    private fun fillTags() {
        tagAdapter = TagAdapter(tagList)
        binding.tagsContainer.apply {
            layoutManager = LinearLayoutManager(this@ArtistActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = tagAdapter
        }
        artist.tags?.tags?.forEach {
            tagList.add(it.name)
            tagAdapter.notifyItemInserted(tagList.size - 1)
        }
    }
}