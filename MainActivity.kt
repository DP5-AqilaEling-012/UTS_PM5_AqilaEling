package com.example.lotsoquiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var soalTextView: TextView
    private lateinit var rg: RadioGroup
    private lateinit var pilihanA: RadioButton
    private lateinit var pilihanB: RadioButton
    private lateinit var pilihanC: RadioButton
    private lateinit var pilihanD: RadioButton
    private lateinit var gambarLotso: ImageView
    private var nomor: Int = 0
    
    private var benar: Int = 0
    private var salah: Int = 0
    
    private val pertanyaanKuis = arrayOf(
        "Apa motivasi utama Lotso dalam menguasai Sunnyside Daycare?",
        "Bagaimana perasaan Lotso terhadap pemiliknya yang lama (Daisy)?",
        "Mengapa Lotso menolak untuk menyelamatkan Woody dan teman-temannya saat mereka terjebak di incinerator?",
        "Apa yang membuat mainan lain di Sunnyside takut pada Lotso?",
        "Bagaimana Lotso berakhir pada akhir Toy Story 3?"
    )

    private val pilihanJawaban = arrayOf(
        "Untuk melindungi mainan baru", "Karena dia ingin mendominasi dan mengendalikan mainan lainnya", "Karena dia ingin menjadi pemimpin yang baik", "Karena dia ingin kembali ke pemilik aslinya",
        "Dia masih mencintainya dan berharap kembali padanya", "Dia merasa kecewa dan terluka setelah ditinggalkan", "Dia tidak peduli lagi dan melupakannya", "Dia menyimpan boneka Daisy sebagai kenangan",
        "Karena dia merasa mereka bukan tanggung jawabnya", "Karena dia ingin membalas dendam kepada Woody", "Karena dia ingin menjadi satu-satunya mainan yang bertahan", "Karena dia sudah putus asa dan kehilangan harapan",
        "Kekuatannya yang luar biasa", "Karismanya yang membuat mereka patuh", "Cara dia manipulasi dan mengontrol situasi", "Kecerdasannya yang lebih unggul dibandingkan mainan lain",
        "Kembali ke Daisy", "Dibuang ke tempat sampah oleh Woody", "Diikat pada truk sampah", "Hidup bahagia di Sunnyside"
    )

    private val jawabanBenar = arrayOf(
        "Karena dia ingin mendominasi dan mengendalikan mainan lainnya",
        "Dia merasa kecewa dan terluka setelah ditinggalkan",
        "Karena dia ingin membalas dendam kepada Woody",
        "Cara dia manipulasi dan mengontrol situasi",
        "Dibuang ke tempat sampah oleh Woody"
    )
    
    private val userAnswers = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        soalTextView = findViewById(R.id.soal)
        rg = findViewById(R.id.pilihan)
        pilihanA = findViewById(R.id.pilihanA)
        pilihanB = findViewById(R.id.pilihanB)
        pilihanC = findViewById(R.id.pilihanC)
        pilihanD = findViewById(R.id.pilihanD)
        gambarLotso = findViewById(R.id.lotsoImage)
        
        loadQuestion()
        
        rg.clearCheck()

       
        benar = 0
        salah = 0
    }

    private fun loadQuestion() {
        soalTextView.text = pertanyaanKuis[nomor]
        pilihanA.text = pilihanJawaban[nomor * 4]
        pilihanB.text = pilihanJawaban[nomor * 4 + 1]
        pilihanC.text = pilihanJawaban[nomor * 4 + 2]
        pilihanD.text = pilihanJawaban[nomor * 4 + 3]
    }

    fun next(view: View) {
        if (rg.checkedRadioButtonId != -1) {
            val selectedOption = findViewById<RadioButton>(rg.checkedRadioButtonId)
            val userAnswer = selectedOption.text.toString()
            
            userAnswers.add(userAnswer)
            
            rg.clearCheck()
            
            if (userAnswer.trim().equals(jawabanBenar[nomor].trim(), ignoreCase = true)) {
                benar++
            } else {
                salah++
            }

            nomor++
            
            if (nomor < pertanyaanKuis.size) {
                loadQuestion()
            } else {
                val hasil = benar * 20

                val intent = Intent(this, HasilKuis::class.java)
                intent.putExtra("benar", benar)
                intent.putExtra("salah", salah)
                intent.putExtra("hasil", hasil)
                intent.putStringArrayListExtra("userAnswers", ArrayList(userAnswers))
                startActivity(intent)
                finish()
            }
        } else {
            Toast.makeText(this, "Kamu harus memilih jawaban!", Toast.LENGTH_SHORT).show()
        }
    }
}
