package com.example.lotsoquiz

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lotsoquiz.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroductionBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)

        // Check and apply dark mode state
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)
        applyDarkMode(isDarkMode)

        binding.switchDarkMode.isChecked = isDarkMode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPreferences.edit()
            editor.putBoolean("darkMode", isChecked)
            editor.apply()
            applyDarkMode(isChecked)
        }

        binding.start.setOnClickListener {
            navigateToMain()
        }
    }

    private fun applyDarkMode(isDarkMode: Boolean) {
        // Ubah background dari layout root
        binding.root.setBackgroundColor(
            if (isDarkMode) resources.getColor(android.R.color.black)
            else resources.getColor(R.color.pink)
        )
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
