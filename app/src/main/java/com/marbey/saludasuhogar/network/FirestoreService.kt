package com.marbey.saludasuhogar.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.model.User

const val HAVEN_COLLECTION_NAME = "havens"
const val GRANDPARENTS_COLLECTION_NAME = "granparents"
const val USER_COLLECTION_NAME = "users"

class FirestoreService {

    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun setDocument(data: Any, collectionName: String, id: String, callback: Callback<Void>){
        firebaseFirestore.collection(collectionName).document(id).set(data)
            .addOnSuccessListener { callback.onSuccess(null) }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun getGrandparent(callback: Callback<List<Grandparent>>){
        firebaseFirestore.collection(GRANDPARENTS_COLLECTION_NAME)
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

    fun findUserByID(id: String, callback : Callback<User>){
        firebaseFirestore.collection(USER_COLLECTION_NAME).document(id)
                .get()
                .addOnSuccessListener { result ->
                    if(result.data != null){
                        callback.onSuccess(result.toObject(User::class.java))
                    } else {
                        callback.onSuccess(null)
                    }
                }
                .addOnFailureListener { exception -> callback.onFailed(exception)}
    }

}