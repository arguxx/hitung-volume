package com.d3if2022.hitungvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.d3if2022.hitungvolume.databinding.ActivityMainBinding

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
        val masukan_x = masukan.toFloat()
        val hasil = masukan_x * masukan_x * masukan_x
        binding.hasil.text = getString(R.string.hasil_x, hasil)
        val sisi = masukan_x * masukan_x
        binding.hasil2.text = getString(R.string.hasil_xx, sisi)

    }
}