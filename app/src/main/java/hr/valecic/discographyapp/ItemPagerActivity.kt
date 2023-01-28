package hr.valecic.discographyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.valecic.discographyapp.adapter.ItemFragmentAdapter
import hr.valecic.discographyapp.databinding.ActivityItemPagerBinding
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.model.Artist

//const val POSITION = "hr.valecic.discographyapp.position"

class ItemPagerActivity : AppCompatActivity() {
//private lateinit var binding: ActivityItemPagerBinding
//private lateinit var artists: MutableList<Artist>
//private lateinit var artist: Artist
//private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityItemPagerBinding.inflate(layoutInflater)
//        setting load animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_item_pager)

        initPager()
        initFragment()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initFragment() {
//        artists = fetchItems()
//        artist = fetchInfo()
//        position = intent.getIntExtra(POSITION, position)
//        binding.viewPager.adapter = ItemFragmentAdapter(this, artists)
//        binding.viewPager.currentItem = position
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
    private fun initPager() {
//        artists = fetchItems()
//        artist = fetchInfo()
//        position = intent.getIntExtra(POSITION, position)
//        binding.viewPager.adapter = ItemFragmentAdapter(this, artists)
//        binding.viewPager.currentItem = position
    }
}