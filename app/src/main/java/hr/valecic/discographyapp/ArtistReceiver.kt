package hr.valecic.discographyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.valecic.discographyapp.framework.startActivity

class ArtistReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context?.startActivity<ArtistActivity>()
    }
}


