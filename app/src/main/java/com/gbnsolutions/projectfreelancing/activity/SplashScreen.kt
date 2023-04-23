package com.gbnsolutions.projectfreelancing.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.gbnsolutions.projectfreelancing.R

class SplashScreen : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 3000L // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            // Start the main activity of your app
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            // Finish the splash activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
