package com.marbey.saludasuhogar.view.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.network.FirestoreService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var firestoreService: FirestoreService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firestoreService = FirestoreService()

        session()

    }

    private fun session() {
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if (email != null && provider != null) {
            startMainActivity(email, ProviderType.valueOf(provider))
        }

    }

    private fun startMainActivity(email: String, provider: ProviderType) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
        finish()
    }

    fun onStartClicked(view: View) {

        val username = username.text.toString()
        val password = password.text.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        view.isEnabled = false
                        startMainActivity(username, ProviderType.BASIC)
                    } else {
                        showErrorMessage(view)
                    }
                }
        } else {
            showEmptyMessage(view)
        }
    }

    private fun showErrorMessage(view: View) {
        Snackbar.make(
            view,
            getString(R.string.error_while_connecting_to_the_account),
            Snackbar.LENGTH_LONG
        )
            .setAction("Info", null).show()
        view.isEnabled = true
    } // Print a message when the connection is failed

    private fun showEmptyMessage(view: View) {
        Snackbar.make(view, "Debes ingresar un email y una contraseña", Snackbar.LENGTH_LONG)
            .setAction("Info", null).show()
        view.isEnabled = true
    } // Print a message when user is empty


}