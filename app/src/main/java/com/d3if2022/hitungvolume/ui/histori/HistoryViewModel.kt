package com.d3if2022.hitungvolume.ui.histori

import androidx.lifecycle.ViewModel
import com.d3if2022.hitungvolume.db.Dao

class HistoryViewModel(db: Dao) : ViewModel() {
    val data = db.getLastData()
}