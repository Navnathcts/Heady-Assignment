package navanth.com.herokuapp.view.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import navanth.com.herokuapp.R
import navanth.com.herokuapp.Utils.openActivity

/**
 * Activity class to present application Splash screen
 * Created by mohitum on 30-11-2017.
 *
 * @version 1.0
 */
class SplashActivity : AppCompatActivity(), Runnable {
    /**
     * Handler to handle the runnable thread on completion
     */
    private var splashTimeHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashTimeHandler = Handler()
        splashTimeHandler?.postDelayed(this,
            SPLASH_RUN_TIME
        )
    }

    override fun run() {
        openActivity(MainActivity::class.java)
        // finish Splash activity.
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        splashTimeHandler?.removeCallbacks(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        splashTimeHandler?.removeCallbacks(this)
    }

    companion object {
        /**
         * Splash display time
         */
        private const val SPLASH_RUN_TIME = 3000L
    }
}