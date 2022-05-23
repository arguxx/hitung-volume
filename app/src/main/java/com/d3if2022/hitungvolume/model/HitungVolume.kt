package com.d3if2022.hitungvolume.model

import com.d3if2022.hitungvolume.db.Entity

fun Entity.hitung(): HasilHitung{
    val masukan =  masukan
    val volume = masukan * masukan
    val luas = masukan * masukan * masukan
    return HasilHitung(masukan, volume, luas)
}