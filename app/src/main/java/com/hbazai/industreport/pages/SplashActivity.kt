package com.hbazai.industreport.pages

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hbazai.industreport.MainActivity
import com.hbazai.industreport.R
import com.hbazai.industreport.pages.user_page.auth.LoginActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class SplashActivity : AppCompatActivity() {

    private lateinit var intentJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val tokenExists = sharedPreferences.contains("token")

        if (tokenExists) {
            // Token exists in shared preferences, do something
            intentJob = CoroutineScope(Dispatchers.Main).launch {
                delay(3000L)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        } else {
            // Token does not exist in shared preferences, do something else
            intentJob = CoroutineScope(Dispatchers.Main).launch {
                delay(3000L)
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }




    }
}