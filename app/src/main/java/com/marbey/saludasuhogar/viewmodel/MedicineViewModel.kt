package com.marbey.saludasuhogar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import java.lang.Exception

class MedicineViewModel: ViewModel() {
    val firestorService = FirestoreService()
    var listMedicine: MutableLiveData<List<Medicine>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(name: String){
        getMedicineFromFirebase(name)
    }

    fun getMedicineFromFirebase(name: String) {
        firestorService.getMedicine(object : Callback<List<Medicine>>{
            override fun onSuccess(result: List<Medicine>?) {
                listMedicine.postValue(result)
                processFinished()
            }
            override fun onFailed(exception: Exception) {
                processFinished()
            }
        }, name)
    }

    fun processFinished(){
        isLoading.value = true
    }

}