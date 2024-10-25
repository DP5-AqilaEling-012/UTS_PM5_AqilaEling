package com.example.lotsoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HasilKuis : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hasilkuis)

        // Mengambil data yang dikirim dari MainActivity
        val benar = intent.getIntExtra("benar", 0)
        val salah = intent.getIntExtra("salah", 0)
        val hasil = intent.getIntExtra("hasil", 0)

        // Menampilkan hasil pada TextView
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

    private fun ulangi() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
