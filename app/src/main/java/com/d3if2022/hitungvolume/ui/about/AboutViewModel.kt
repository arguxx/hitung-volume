package com.d3if2022.hitungvolume.ui.about

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if2022.hitungvolume.model.ApiModel
import com.d3if2022.hitungvolume.network.AboutApi
import com.d3if2022.hitungvolume.network.ApiStatus
import com.d3if2022.hitungvolume.network.UpdateWorker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AboutViewModel : ViewModel(){
    private val status = MutableLiveData<ApiStatus>()
    private val userModel: MutableLiveData<ApiModel> by lazy {
        MutableLiveData<ApiModel>().also {
            retrieveData()
        }
    }
    fun getUser(): LiveData<ApiModel> {
        return userModel
    }
    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                val result = AboutApi.service.getAbout()
                userModel.postValue(result)
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("AboutViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }
    fun getStatus(): LiveData<ApiStatus> = status

}