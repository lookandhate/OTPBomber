package com.example.b0mb3r.models

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.b0mb3r.services.Services
import com.example.b0mb3r.services.BaseService


class ServiceViewModel(application: Application): AndroidViewModel(application) {
    var mapOfServices = MutableLiveData(Services(application.applicationContext).mapOfServices)
    fun GetCheckedServices(): List<BaseService>? {
        return mapOfServices.value?.keys?.filter { mapOfServices.value!![it] == true }
    }
}