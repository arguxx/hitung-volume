package com.d3if2022.hitungvolume.ui.hitung

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if2022.hitungvolume.MainActivity
import com.d3if2022.hitungvolume.db.Dao
import com.d3if2022.hitungvolume.db.Entity
import com.d3if2022.hitungvolume.model.HasilHitung
import com.d3if2022.hitungvolume.model.hitung
import com.d3if2022.hitungvolume.network.UpdateWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

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
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES) .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request )
    }
}
