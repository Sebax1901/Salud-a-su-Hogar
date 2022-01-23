package com.marbey.saludasuhogar.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.GRANDPARENTS_COLLECTION_NAME

class GrandparentViewModel: ViewModel() {
    private val firestoreService = FirestoreService()
    var listGrandparent: MutableLiveData<List<Grandparent>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(name: String){
        getGrandParentFromFirebase(name)
    }

    private fun getGrandParentFromFirebase(haven: String){
        firestoreService.getGrandparent(object: Callback<List<Grandparent>> {
            override fun onSuccess(result: List<Grandparent>?) {
                listGrandparent.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        }, haven)
    }

    fun processFinished(){
        isLoading.value = true
    }

    fun deleteGrandparent(name: String, view: View){
        firestoreService.deleteDocument(GRANDPARENTS_COLLECTION_NAME,name, object: Callback<Void>{
            override fun onSuccess(result: Void?) {
                Snackbar.make(view,"Se ha eliminado el Paciente $name", Snackbar.LENGTH_LONG).show()
            }

            override fun onFailed(exception: Exception) {
                Snackbar.make(view,"Error al eliminar el paciente $name", Snackbar.LENGTH_LONG).show()
            }
        })
    }

}