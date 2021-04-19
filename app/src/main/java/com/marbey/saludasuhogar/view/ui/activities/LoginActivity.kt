package com.marbey.saludasuhogar.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.User
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.USER_COLLECTION_NAME
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var firestoreService : FirestoreService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firestoreService = FirestoreService()
    }

    fun onStartClicked(view: View){
        view.isEnabled = false
        auth.signInAnonymously()
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val username = username.text.toString()
                    firestoreService.findUserByID(username, object : Callback<User>{
                        override fun onSuccess(result: User?) {
                            if (result == null){
                                var user = User()
                                user.username = username
                                saveUserAndStartMainActivity(user, view)
                            } else {
                                startMainActivity(username)
                            }
                        }
                        override fun onFailed(exception: Exception) {
                            showErrorMessage(view)
                        }
                    })
                } else {
                    showErrorMessage(view)
                    view.isEnabled = true
                }
            }
    }

    private fun saveUserAndStartMainActivity(user: User, view: View) {
        firestoreService.setDocument(user, USER_COLLECTION_NAME, user.username, object : Callback<Void>{
            override fun onSuccess(result: Void?) {
                startMainActivity(user.username)
            }

            override fun onFailed(exception: Exception) {
                showErrorMessage(view)
                Log.e(TAG, "Error", exception)
                view.isEnabled = true
            }
        })
    }

    private fun showErrorMessage(view: View) {
        Snackbar.make(view, getString(R.string.error_while_connecting_to_the_server), Snackbar.LENGTH_LONG)
            .setAction("Info", null).show()
    }

    private fun startMainActivity(username : String) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}