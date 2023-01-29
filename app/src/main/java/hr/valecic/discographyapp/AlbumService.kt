package hr.valecic.discographyapp

import android.content.Intent
import androidx.core.app.JobIntentService
import hr.valecic.discographyapp.api.ArtistFetcher

//private const val JOB_ID = 1
private const val NAME = "name"
@Suppress("DEPRECATION")
class AlbumService() : JobIntentService(){
    override fun onHandleWork(intent: Intent) {
        val params = intent.extras
        var name = ""

        if (params != null) name = params.getString(NAME).toString()

        ArtistFetcher(this).fetchAlbums(name)
    }
}