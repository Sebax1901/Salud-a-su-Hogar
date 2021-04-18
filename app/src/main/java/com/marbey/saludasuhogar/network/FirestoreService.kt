package com.marbey.saludasuhogar.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven

class FirestoreService {

    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getGranparent(callback: Callback<List<Grandparent>>){
        firebaseFirestore.collection("conferences")
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result){
                        val list = result.toObjects(Grandparent::class.java)
                        callback.onSuccess(list)
                    }
                }
    }

    fun getHaven(callback: Callback<List<Haven>>){
        firebaseFirestore.collection("havens")
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result){
                        val list = result.toObjects(Haven::class.java)
                        callback.onSuccess(list)
                    }
                }
    }

}