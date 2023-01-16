package hr.valecic.discographyapp.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import hr.valecic.discographyapp.DiscogReceiver
import hr.valecic.discographyapp.HostActivity
import hr.valecic.discographyapp.R

fun View.applyAnimation(animationId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, animationId))

inline fun <reified T : Activity> Context.startActivity() = startActivity(
    Intent(this, HostActivity::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
)

fun Context.setBooleanPreference(key: String, value: Boolean = true) {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()
}

fun Context.getBooleanPreference(key: String): Boolean =
    PreferenceManager.getDefaultSharedPreferences(this).getBoolean(key, false)

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService<ConnectivityManager>()
    connectivityManager?.activeNetwork?.let { network ->
        connectivityManager.getNetworkCapabilities(network)?.let { cap ->
            return cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}

fun callDelayed(delay: Long, runnable: Runnable) {
    Handler(Looper.getMainLooper()).postDelayed(
        runnable,
        delay
    )
}

inline fun<reified T: BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, DiscogReceiver::class.java))