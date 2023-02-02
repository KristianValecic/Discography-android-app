package hr.valecic.discographyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.valecic.discographyapp.databinding.ActivityLoadBinding
import hr.valecic.discographyapp.framework.callDelayed
import hr.valecic.discographyapp.framework.fetchItems
import hr.valecic.discographyapp.framework.isOnline
import java.util.Timer
import java.util.zip.Inflater
import java.util.TimerTask
import kotlin.concurrent.timerTask

private const val DELAY = 3000L

class LoadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadBinding
    private var position = 0
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startProgressBar()
        loadArtist()
    }

    private fun loadArtist() {
        if(isOnline()){
            name = intent.getStringExtra(POSITION).toString()
            ArtistActivity.position = position
            ArtistService.enqueueGetArtistInfo(this, fetchItems().find { it.name == name }?.name ?: "")
        }else{
            binding.tvLoadActivity.text = getString(R.string.no_internet)
            callDelayed(DELAY) { finish() }
        }
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