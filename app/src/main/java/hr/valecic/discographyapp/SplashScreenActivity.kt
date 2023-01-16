package hr.valecic.discographyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import hr.valecic.discographyapp.databinding.ActivitySplashScreenBinding
import hr.valecic.discographyapp.framework.*

private const val DELAY = 3000L
public const val DATA_IMPORTED = "hr.algebra.nasa.data_imported"
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private val currentApiVersion = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()
        startAnimatoins()
        redirect()
    }

    private fun hideSystemUI() {
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }

    private fun startAnimatoins() {
        binding.tvSplash.applyAnimation(R.anim.blink)
        binding.ivSplash.applyAnimation(R.anim.rotate)
    }

    private fun redirect() {
        if(getBooleanPreference(DATA_IMPORTED)){
            callDelayed(DELAY) { startActivity<HostActivity>() }
        }else{
            if(isOnline()){
                DiscogService.enqueue(this)
            }else{
                binding.tvSplash.text = getString(R.string.no_internet)
                callDelayed(DELAY) { finish() }
            }
        }
    }
}