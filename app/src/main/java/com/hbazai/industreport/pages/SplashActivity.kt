package com.hbazai.industreport.pages

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hbazai.industreport.MainActivity
import com.hbazai.industreport.R
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class SplashActivity : AppCompatActivity() {

    private lateinit var intentJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        intentJob = CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}