package hr.valecic.discographyapp

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.valecic.discographyapp.api.DiscogApi
import hr.valecic.discographyapp.api.DiscogFetcher
import hr.valecic.discographyapp.framework.sendBroadcast

private const val JOB_ID = 1
@Suppress("DEPRECATION")
class DiscogService: JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        //Thread.sleep(6000)
        DiscogFetcher(this).fetchItems()
        //sendBroadcast(Intent(this, DiscogReceiver::class.java))
        //sendBroadcast<DiscogReceiver>()
    }
    companion object{
        fun enqueue(context: Context){
            enqueueWork(context, DiscogService::class.java, JOB_ID,
                Intent(context, DiscogService::class.java))
        }
    }
}