package hr.valecic.discographyapp

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.core.app.JobIntentService
import androidx.work.*
import hr.valecic.discographyapp.api.ArtistFetcher
import hr.valecic.discographyapp.framework.sendBroadcast


private const val JOB_ID = 1
private const val NAME = "name"
@Suppress("DEPRECATION")
class ArtistService() : JobIntentService(){

    //    lateinit var artistName: String
    override fun onHandleWork(intent: Intent) {
        val b = intent.extras
        var name = "" // or other values

        if (b != null) name = b.getString(NAME).toString()

        ArtistFetcher(this).fetchInfo(name)
//        sendBroadcast(Intent(this, ArtistReciever::class.java))
//        sendBroadcast<ArtistReciever>()
    }

    companion object {
        fun enqueueGetArtistInfo(context: Context, name: String) {
//            val compressionWork = OneTimeWorkRequest.Builder(ArtistService::class.java)
//            val data = Data.Builder()
////Add parameter in Data class. just like bundle. You can also add Boolean and Number in parameter.
//            data.putString(NAME, name)
////Set Input Data
//            compressionWork.setInputData(data.build())
////enque worker
//            WorkManager.getInstance().enqueue(compressionWork.build())

            val intent = Intent(context, ArtistService::class.java)
            val b = Bundle()
            b.putString(NAME, name)

            intent.putExtras(b) //Put your id to your next Intent

            enqueueWork(
                context, ArtistService::class.java, JOB_ID, intent
            )
        }
    }

//    override fun doWork(): Result {
//        inputData.getString(NAME)?.let { ArtistFetcher(this).fetchInfo(it) }
//    }

}