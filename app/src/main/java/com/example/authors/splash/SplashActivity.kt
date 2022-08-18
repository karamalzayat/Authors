package com.example.authors.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.example.authors.R
import com.example.authors.databinding.ActivitySplashBinding
import com.example.authors.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(R.drawable.loading).into(binding.splashImgLoading)
    }

    override fun onStart() {
        super.onStart()
        openHome()
    }

    private fun openHome() {
        Handler().postDelayed(Runnable {
            startActivity(
                Intent(
                    this@SplashActivity,
                    HomeActivity::class.java
                )
            )
            finish()
        }, 4000)
    }

}