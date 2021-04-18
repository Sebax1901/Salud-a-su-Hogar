package com.marbey.saludasuhogar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import java.lang.Exception

class GrandparentViewModel: ViewModel() {
    val firestoreService = FirestoreService()
    var listGrandparent: MutableLiveData<List<Grandparent>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getGranparentFromFirebase()
    }

    fun getGranparentFromFirebase(){
        firestoreService.getGranparent(object: Callback<List<Grandparent>> {
            override fun onSuccess(result: List<Grandparent>?) {
                listGrandparent.postValue(result)
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