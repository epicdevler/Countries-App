package com.kulex.explorecountries.presentations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kulex.explorecountries.R
import com.kulex.explorecountries.databinding.MainActivityLayoutBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}