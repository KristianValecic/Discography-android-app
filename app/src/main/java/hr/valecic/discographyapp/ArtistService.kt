package hr.valecic.discographyapp

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.core.app.JobIntentService
import hr.valecic.discographyapp.api.ArtistFetcher
import hr.valecic.discographyapp.framework.sendBroadcast


private const val JOB_ID = 1
private const val NAME = "name"
@Suppress("DEPRECATION")
class ArtistService() : JobIntentService(){
    override fun onHandleWork(intent: Intent) {
        val b = intent.extras
        var name = ""

        if (b != null) name = b.getString(NAME).toString()

        ArtistFetcher(this).fetchInfo(name)
        ArtistFetcher(this).fetchAlbums(name)
    }

    companion object {
        fun enqueueGetArtistInfo(context: Context, name: String) {
            val intent = Intent(context, ArtistService::class.java)
            val b = Bundle()
            b.putString(NAME, name)

            intent.putExtras(b) //Put your id to your next Intent

            enqueueWork(
                context, ArtistService::class.java, JOB_ID, intent
            )
        }
    }
}