package com.marbey.saludasuhogar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import java.lang.Exception

class HavenViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listHaven: MutableLiveData<List<Haven>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getHavenFromFirebase()
    }

    fun getHavenFromFirebase(){
        firestoreService.getHaven(object: Callback<List<Haven>> {
            override fun onSuccess(result: List<Haven>?) {
                listHaven.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }

}