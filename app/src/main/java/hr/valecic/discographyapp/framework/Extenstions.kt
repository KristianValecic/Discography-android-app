package hr.valecic.discographyapp.framework

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ClipData.Item
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
import hr.valecic.discographyapp.DISCOG_PROVIDER_CONTENT_URI
import hr.valecic.discographyapp.DiscogReceiver
import hr.valecic.discographyapp.HostActivity
import hr.valecic.discographyapp.R
import hr.valecic.discographyapp.api.ArtistItem
import hr.valecic.discographyapp.model.Artist

fun View.applyAnimation(animationId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, animationId))

inline fun <reified T : Activity> Context.startActivity() = startActivity(
    Intent(this, HostActivity::class.java)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
)

inline fun <reified T : Activity> Context.startActivity(key: String, value: Int) = startActivity(
    Intent(this, HostActivity::class.java).apply {
        putExtra(key, value)
    }.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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

@SuppressLint("Range")
fun Context.fetchItems(): MutableList<Artist>{
    val artists = mutableListOf<Artist>()
    val cursor = contentResolver.query(DISCOG_PROVIDER_CONTENT_URI,
        null, null, null, null)
    while (cursor != null && cursor.moveToNext()) {
        artists.add(Artist(
            cursor.getLong(cursor.getColumnIndex(Artist::_id.name)),
            cursor.getString(cursor.getColumnIndex(Artist::name.name)),
//            cursor.getString(cursor.getColumnIndex(Artist::image.name)),
            cursor.getInt(cursor.getColumnIndex(Artist::streamable.name)) == 1,
            cursor.getString(cursor.getColumnIndex(Artist::match.name)),
            cursor.getInt(cursor.getColumnIndex(Artist::favorite.name)) == 1
        ))
    }
    return artists
}
