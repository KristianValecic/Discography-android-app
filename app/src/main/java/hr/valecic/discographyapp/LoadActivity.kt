package hr.valecic.discographyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.valecic.discographyapp.databinding.ActivityLoadBinding
import hr.valecic.discographyapp.framework.fetchItems
import java.util.Timer
import java.util.zip.Inflater
import java.util.TimerTask
import kotlin.concurrent.timerTask

class LoadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadBinding
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startProgressBar()
        loadArtist()
    }

    private fun loadArtist() {
        position = intent.getIntExtra(POSITION, position)
        ArtistService.enqueueGetArtistInfo(this, fetchItems()[position].name)
//        ArtistService.enqueueGetArtistAlbums(this, fetchItems()[position].name)
    }

    private fun startProgressBar() {
        val progressBar = binding.progressBar
        val timer = Timer()
        var count = 0
        val timerTask = timerTask(){
            count++
            progressBar.progress = count
            if (count == 100) {
                timer.cancel()
            }
        }
        timer.schedule(timerTask, 0, 100)
    }
}