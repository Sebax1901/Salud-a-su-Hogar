package com.marbey.saludasuhogar.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.MEDICINE_COLLECTION_NAME
import java.lang.Exception
import kotlin.math.log

class MedicineViewModel: ViewModel() {
    val firestorService = FirestoreService()
    var listMedicine: MutableLiveData<List<Medicine>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(name: String){
        getMedicineFromFirebase(name)
    }

    private fun getMedicineFromFirebase(name: String) {
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

    fun setMedicineFromFirebase(data: Any, id : String){
        firestorService.setDocument(data, MEDICINE_COLLECTION_NAME,id,object : Callback<Void>{
            override fun onSuccess(result: Void?) {

            }

            override fun onFailed(exception: Exception) {

            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }

}