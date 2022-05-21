package com.d3if2022.hitungvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.d3if2022.hitungvolume.databinding.ActivityMainBinding
import com.d3if2022.hitungvolume.model.HasilHitung

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cari.setOnClickListener { cari() }
        binding.Clear.setOnClickListener {
            binding.minInp.setText("")
            binding.hasil.setText("")
            binding.hasil2.setText("")


        }
    }
    private fun cari() {
        val masukan = binding.minInp.text.toString()
        if (TextUtils.isEmpty(masukan)) {
            Toast.makeText(this, R.string.blank, Toast.LENGTH_LONG).show()
            return
        }
        val result = hitung(
            masukan.toFloat()
        )
        showResult(result)
    }
    private fun hitung(masukan: Float): HasilHitung{
        val hasilSisi = masukan * masukan
        val hasilVolume = masukan * masukan * masukan
        return HasilHitung(hasilSisi, hasilVolume)
    }
    private fun showResult(result: HasilHitung) {
        binding.hasil.text = getString(R.string.hasil_x, result.hasilVolume)
        binding.hasil2.text = getString(R.string.hasil_xx, result.hasilSisi)
    }
}