package com.example.belajarandorid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.graphics.Color
import android.content.res.ColorStateList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val prefs = getSharedPreferences(
            "data_aplikasi",
            MODE_PRIVATE
        )
        val judulTema = findViewById<TextView>(R.id.txtSalam)
        val tombolGantiTema = findViewById<Button>(R.id.btnTema)
        val teksRiwayat = findViewById<TextView>(R.id.txtRiwayat)
        teksRiwayat.text = prefs.getString(
            "riwayat_aktivitas",
            "Belum ada aktivitas."
        ) ?: "Belum ada aktivitas."

        val layoutUtamaTema =
            findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)



        // Menentukan tema awal aplikasi
        var modeGelap = prefs.getBoolean("mode_gelap", false)

// Mengambil wadah konten di dalam ScrollView
        val kontenUtama = findViewById<
                androidx.constraintlayout.widget.ConstraintLayout
                >(R.id.kontenUtama)

// Mengambil komponen yang warnanya akan berubah
        val subtitle = findViewById<TextView>(R.id.txtSubtitle)
        val counter = findViewById<TextView>(R.id.txtCounter)
        val labelNama = findViewById<TextView>(R.id.lblNama)
        val input = findViewById<EditText>(R.id.inputNama)

        val tombolKlik = findViewById<Button>(R.id.btnKlik)
        val tombolResetTema = findViewById<Button>(R.id.btnReset)
        val tombolSapaTema = findViewById<Button>(R.id.btnSapa)
        val tombolGantiPengguna =
            findViewById<Button>(R.id.btnHapusNama)

        // Fungsi untuk mengubah warna tombol
        fun aturWarnaTombol(tombol: Button, warna: String) {
            tombol.backgroundTintList = ColorStateList.valueOf(
                Color.parseColor(warna)
            )
        }

        // Fungsi untuk menerapkan warna tema
        fun terapkanTema(gelap: Boolean) {
            if (gelap) {
                // Latar halaman dan kartu
                layoutUtamaTema.setBackgroundColor(
                    Color.parseColor("#121212")
                )
                kontenUtama.setBackgroundColor(
                    Color.parseColor("#121212")
                )
                counter.setBackgroundColor(
                    Color.parseColor("#242424")
                )
                teksRiwayat.setBackgroundColor(
                    Color.parseColor("#242424")
                )


                // Warna teks
                judulTema.setTextColor(Color.WHITE)
                subtitle.setTextColor(Color.LTGRAY)
                counter.setTextColor(Color.WHITE)
                labelNama.setTextColor(Color.LTGRAY)
                teksRiwayat.setTextColor(Color.LTGRAY)

                // Input nama
                input.setTextColor(Color.WHITE)
                input.setHintTextColor(Color.LTGRAY)
                input.backgroundTintList =
                    ColorStateList.valueOf(Color.LTGRAY)

                // Warna tombol
                aturWarnaTombol(tombolKlik, "#315EFB")
                aturWarnaTombol(tombolResetTema, "#343A46")
                aturWarnaTombol(tombolSapaTema, "#315EFB")
                aturWarnaTombol(tombolGantiPengguna, "#343A46")
                aturWarnaTombol(tombolGantiTema, "#7254D8")

                tombolResetTema.setTextColor(Color.WHITE)
                tombolGantiPengguna.setTextColor(Color.WHITE)

                tombolGantiTema.text = "Ganti ke Mode Terang"

            } else {
                // Mengembalikan latar terang
                layoutUtamaTema.setBackgroundColor(Color.WHITE)
                kontenUtama.setBackgroundColor(
                    Color.parseColor("#F3F5FA")
                )
                counter.setBackgroundColor(Color.WHITE)

                // Warna teks
                judulTema.setTextColor(
                    Color.parseColor("#18243A")
                )
                subtitle.setTextColor(
                    Color.parseColor("#7A8498")
                )
                counter.setTextColor(
                    Color.parseColor("#233A63")
                )
                labelNama.setTextColor(
                    Color.parseColor("#65718A")
                )
                teksRiwayat.setBackgroundColor(Color.WHITE)
                teksRiwayat.setTextColor(
                    Color.parseColor("#65718A")
                )

                // Input nama
                input.setTextColor(
                    Color.parseColor("#18243A")
                )
                input.setHintTextColor(
                    Color.parseColor("#8993A6")
                )
                input.backgroundTintList =
                    ColorStateList.valueOf(
                        Color.parseColor("#315EFB")
                    )

                // Mengembalikan warna tombol
                aturWarnaTombol(tombolKlik, "#315EFB")
                aturWarnaTombol(tombolResetTema, "#E3E9F4")
                aturWarnaTombol(tombolSapaTema, "#315EFB")
                aturWarnaTombol(tombolGantiPengguna, "#E3E9F4")
                aturWarnaTombol(tombolGantiTema, "#7254D8")

                tombolResetTema.setTextColor(
                    Color.parseColor("#35445F")
                )
                tombolGantiPengguna.setTextColor(
                    Color.parseColor("#35445F")
                )

                tombolGantiTema.text = "Ganti ke Mode Gelap"
            }
        }

// Menjalankan pergantian tema saat tombol ditekan
        tombolGantiTema.setOnClickListener {
            modeGelap = !modeGelap

            prefs.edit()
                .putBoolean("mode_gelap", modeGelap)
                .apply()

            terapkanTema(modeGelap)
            catatAktivitas(
                if (modeGelap) "Mengaktifkan mode gelap"
                else "Mengaktifkan mode terang"
            )
        }
        val inputNama = findViewById<EditText>(R.id.inputNama)
        val teks = findViewById<TextView>(R.id.txtSalam)

        val namaTersimpan = prefs.getString("nama_pengguna", "") ?: ""

        inputNama.setText(namaTersimpan)

        if (namaTersimpan.isNotBlank()) {
            teks.text = "Halo, $namaTersimpan!"
        }
        val tombolHapusNama =
            findViewById<Button>(R.id.btnHapusNama)

        tombolHapusNama.setOnClickListener {
            prefs.edit()
                .remove("nama_pengguna")
                .apply()

            inputNama.text.clear()
            inputNama.error = null
            teks.text = "Hello Adel Ganteng!"

            catatAktivitas("Mengganti Pengguna")
        }
        val tombolSapa = findViewById<Button>(R.id.btnSapa)
        val tombol = findViewById<Button>(R.id.btnKlik)
        val counterText = findViewById<TextView>(R.id.txtCounter)
        val tombolReset = findViewById<Button>(R.id.btnReset)
        var jumlahKlik = prefs.getInt("jumlah_klik", 0)
        counterText.text = "Jumlah klik: $jumlahKlik"

        var sudahDiklik = false

        tombol.setOnClickListener {
            jumlahKlik++
            catatAktivitas("Menekan tombol klik")
            prefs.edit()
                .putInt("jumlah_klik", jumlahKlik)
                .apply()
            counterText.text = "Jumlah klik: $jumlahKlik"
            if (sudahDiklik) {
                teks.text = "Hello Adel Ganteng!"
                tombol.text = "Klik Aku"
                sudahDiklik = false
            } else {
                teks.text = "Android Developer Masa Depan!"
                tombol.text = "Kembali"
                sudahDiklik = true
            }
        }
        tombolReset.setOnClickListener {
            jumlahKlik = 0
            catatAktivitas("Mereset penghitung")
            counterText.text = "Jumlah klik: 0"
            prefs.edit()
                .putInt("jumlah_klik", 0)
                .apply()

            teks.text = "Hello Adel Ganteng!"
            inputNama.text.clear()
            inputNama.error = null
        }
        tombolSapa.setOnClickListener {
            val nama = inputNama.text.toString().trim()

            if (nama.isEmpty()) {
                inputNama.error = "Nama tidak boleh kosong"
            } else {
                teks.text = "Halo, $nama!"

                prefs.edit()
                    .putString("nama_pengguna", nama)
                    .apply()

                inputNama.error=null
                catatAktivitas("Menyapa$nama")
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun catatAktivitas(aktivitas: String) {
        val prefs = getSharedPreferences(
            "data_aplikasi",
            MODE_PRIVATE
        )

        val riwayatLama = prefs.getString(
            "riwayat_aktivitas",
            ""
        ) ?: ""

        val waktu = java.text.SimpleDateFormat(
            "HH:mm:ss",
            java.util.Locale.getDefault()
        ).format(java.util.Date())

        val entriBaru = "• $waktu  $aktivitas"

        val riwayatBaru = if (riwayatLama.isBlank()) {
            entriBaru
        } else {
            "$entriBaru\n$riwayatLama"
        }

        val baris = riwayatBaru.lines().take(10)

        prefs.edit()
            .putString(
                "riwayat_aktivitas",
                baris.joinToString("\n")
            )
            .apply()
    }
}
