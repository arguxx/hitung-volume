package com.d3if2022.hitungvolume.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if2022.hitungvolume.model.HasilHitung

class MainViewModel : ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung?>()

    fun hitung(masukan: Float){
        val hasilSisi = masukan * masukan
        val hasilVolume = masukan * masukan * masukan
        hasilHitung.value = HasilHitung(hasilSisi, hasilVolume)
    }
    fun getHasilHitung(): LiveData<HasilHitung?> = hasilHitung
}