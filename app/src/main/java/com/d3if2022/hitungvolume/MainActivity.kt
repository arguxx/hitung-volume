package com.d3if2022.hitungvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.d3if2022.hitungvolume.databinding.ActivityMainBinding
import com.d3if2022.hitungvolume.model.HasilHitung

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getHasilHitung().observe(this, { showResult(it) })

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
        viewModel.hitung(
            masukan.toFloat()
        )
    }

    private fun showResult(result: HasilHitung?) {
        if (result == null) return
        binding.hasil.text = getString(R.string.hasil_x, result.hasilVolume)
        binding.hasil2.text = getString(R.string.hasil_xx, result.hasilSisi)
    }
}