package com.marbey.saludasuhogar.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.model.User

const val HAVEN_COLLECTION_NAME = "havens"
const val GRANDPARENTS_COLLECTION_NAME = "grandparents"
const val MEDICINE_COLLECTION_NAME = "medicines"

class FirestoreService {


    private val firebaseFirestore = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun setDocument(data: Any, collectionName: String, id: String, callback: Callback<Void>){
        firebaseFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun deleteDocument(collectionName: String,id: String,callback: Callback<Void>){
        firebaseFirestore.collection(collectionName).document(id).delete()
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun getGrandparent(callback: Callback<List<Grandparent>>, haven: String){
        firebaseFirestore.collection(GRANDPARENTS_COLLECTION_NAME)
                .whereEqualTo("haven", haven)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result){
                        val list = result.toObjects(Grandparent::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
                .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun getMedicine(callback: Callback<List<Medicine>>, grandparent: String){
        firebaseFirestore.collection(MEDICINE_COLLECTION_NAME)
                .whereEqualTo("grandparent", grandparent)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result){
                        val list = result.toObjects(Medicine::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
                .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun getHaven(callback: Callback<List<Haven>>){
        firebaseFirestore.collection(HAVEN_COLLECTION_NAME)
                .get()
                .addOnSuccessListener { result ->
                    for (doc in result){
                        val list = result.toObjects(Haven::class.java)
                        callback.onSuccess(list)
                        break
                    }
                }
                .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

}