package com.example.authors.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
//import android.support.v7.graphics.Palette
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.authors.databinding.ActivitySelectedPhotoBinding


class SelectedPhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectedPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectedPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val selectedAuthor = intent.getSerializableExtra("selectedAuthor") as? AuthorModel
        Glide.with(this)
            .asBitmap()
            .error(com.google.android.material.R.drawable.mtrl_ic_error)
            .placeholder(com.example.authors.R.drawable.avd_loading_anim)
            .load(selectedAuthor?.authorDownloadUrl)
            .into(binding.selectedAuthorImage)

//        val bitmap1 =
//            BitmapFactory.decodeResource(resources, com.example.authors.R.drawable.avd_loading_anim)
//        createPaletteAsync(bitmap1)
//
//
//    }
//
//    private fun createPaletteAsync(bitmap1: Bitmap) {
//        Palette.from(bitmap1).generate { p ->
//            val defaultValue = 0x000000
//            if (p != null) {
//                binding.bg.setBackgroundColor(p.getDominantColor(defaultValue))
//            }
//        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}