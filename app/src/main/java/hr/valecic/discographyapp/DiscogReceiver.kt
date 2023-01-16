package hr.valecic.discographyapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.valecic.discographyapp.framework.setBooleanPreference
import hr.valecic.discographyapp.framework.startActivity

class DiscogReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}