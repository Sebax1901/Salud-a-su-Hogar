package com.marbey.saludasuhogar.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.HAVEN_COLLECTION_NAME

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

    fun deleteHaven(name: String, view: View){
        firestoreService.deleteDocument(HAVEN_COLLECTION_NAME,name, object: Callback<Void>{
            override fun onSuccess(result: Void?) {
                refresh()
                Snackbar.make(view,"Se ha eliminado el hogar $name",Snackbar.LENGTH_LONG).show()
            }

            override fun onFailed(exception: Exception) {
                Snackbar.make(view,"Error al eliminar el hogar $name",Snackbar.LENGTH_LONG).show()
            }
        })
    }

}