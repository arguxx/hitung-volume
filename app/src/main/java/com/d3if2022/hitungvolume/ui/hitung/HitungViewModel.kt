package com.d3if2022.hitungvolume.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if2022.hitungvolume.db.Dao
import com.d3if2022.hitungvolume.db.Entity
import com.d3if2022.hitungvolume.model.HasilHitung
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HitungViewModel(private val db: Dao) : ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung?>()
    val data = db.getLastData()

    fun hitung(masukan: Float){
        val hasilSisi = masukan * masukan
        val hasilVolume = masukan * masukan * masukan
        hasilHitung.value = HasilHitung(hasilSisi, hasilVolume)

        viewModelScope.launch {
            withContext(
                Dispatchers.IO) {

                val data = Entity(
                    input = masukan,
                    volume = hasilVolume,
                    luas = hasilSisi
                )
                db.insert(data) }
        }
    }
    fun getHasilHitung(): LiveData<HasilHitung?> = hasilHitung
}