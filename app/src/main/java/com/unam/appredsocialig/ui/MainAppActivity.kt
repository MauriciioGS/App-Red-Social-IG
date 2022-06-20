package com.unam.appredsocialig.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unam.appredsocialig.R
import com.unam.appredsocialig.databinding.ActivityMainBinding

class MainAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppRedSocialIG)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}