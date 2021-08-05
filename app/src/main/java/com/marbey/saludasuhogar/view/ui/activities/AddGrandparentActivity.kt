package com.marbey.saludasuhogar.view.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.GRANDPARENTS_COLLECTION_NAME
import com.marbey.saludasuhogar.network.HAVEN_COLLECTION_NAME
import com.marbey.saludasuhogar.view.ui.fragments.HavenFragment
import java.lang.Exception

class AddGrandparentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grandparent)

    }

    fun onAddGrandparentClick(view: View){

        val grandparent = Grandparent()

        grandparent.name = findViewById<EditText>(R.id.GrandparentNameAdd).text.toString()
        val age = findViewById<EditText>(R.id.grandParentAgeAdd).text.toString()
        val havenName = intent.extras?.getString("havenName")
        grandparent.haven = havenName.toString()

        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        if (grandparent.name.isEmpty() || age.isEmpty()){
            view.hideKeyboard()
            Snackbar.make(view, "Debes ingresar un nombre y una edad para el paciente", Snackbar.LENGTH_LONG).show()
        } else  {
            view.hideKeyboard()
            grandparent.age = age.toInt()
            val firestoreService = FirestoreService()
            firestoreService.setDocument(grandparent, GRANDPARENTS_COLLECTION_NAME,grandparent.name, object : Callback<Void>{
                override fun onSuccess(result: Void?) {
                    Snackbar.make(view,"Paciente Creado",Snackbar.LENGTH_LONG).show()

                }

                override fun onFailed(exception: Exception) {
                    showMessageError(view)
                }

            })

            Handler().postDelayed({
                val fragment = HavenFragment()
                finish()
            }, 1000)
        }

    }

    fun showMessageError(view : View){
        Snackbar.make(view, "El paciente no ha sido creado", Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}