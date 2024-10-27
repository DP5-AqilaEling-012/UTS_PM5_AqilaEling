package com.example.lotsoquiz

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View // Tambahkan impor ini untuk menggunakan View
import android.widget.Button
import android.widget.TextView

class HasilKuis : Activity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hasilkuis)

        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("darkMode", false)
        applyDarkMode(isDarkMode)

        val benar = intent.getIntExtra("benar", 0)
        val salah = intent.getIntExtra("salah", 0)
        val hasil = intent.getIntExtra("hasil", 0)

        val hasilTextView = findViewById<TextView>(R.id.hasil)
        val nilaiTextView = findViewById<TextView>(R.id.nilai)

        hasilTextView.text = """
            Jawaban Benar: $benar
            Jawaban Salah: $salah
        """.trimIndent()

        nilaiTextView.text = hasil.toString()

        val btnUlangi = findViewById<Button>(R.id.ulangi)
        btnUlangi.setOnClickListener { ulangi() }
    }

    private fun applyDarkMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            findViewById<View>(R.id.resultLayout).setBackgroundColor(resources.getColor(android.R.color.black))
        } else {
            findViewById<View>(R.id.resultLayout).setBackgroundColor(resources.getColor(R.color.pink))
        }
    }

    private fun ulangi() {
        val intent = Intent(applicationContext, IntroductionActivity::class.java)
        startActivity(intent)
        finish()
    }
}
