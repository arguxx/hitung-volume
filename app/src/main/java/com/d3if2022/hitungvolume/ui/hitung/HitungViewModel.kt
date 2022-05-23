package com.d3if2022.hitungvolume.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if2022.hitungvolume.db.Dao
import com.d3if2022.hitungvolume.db.Entity
import com.d3if2022.hitungvolume.model.HasilHitung
import com.d3if2022.hitungvolume.model.hitung
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HitungViewModel(private val db: Dao) : ViewModel() {
    private val hasilHitung = MutableLiveData<HasilHitung?>()

    fun hitung(masukan: Float){
        val data = Entity(
            masukan = masukan,
            volume = masukan * masukan * masukan,
            luas = masukan * masukan
        )
        hasilHitung.value = data.hitung()

        viewModelScope.launch {
            withContext(
                Dispatchers.IO) {
                db.insert(data) }
        }
    }
    fun getHasilHitung(): LiveData<HasilHitung?> = hasilHitung
}
