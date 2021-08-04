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
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.HAVEN_COLLECTION_NAME
import com.marbey.saludasuhogar.view.ui.fragments.HomeFragment
import java.lang.Exception

class AddHavenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_haven)
    }

    fun onAddHavenClick(view: View){

        val haven = Haven()
        haven.name = findViewById<EditText>(R.id.havenNameAdd).text.toString()
        haven.nurseName = findViewById<EditText>(R.id.nurseNameAdd).text.toString()

        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        if (haven.name.isEmpty() || haven.nurseName.isEmpty()){
            view.hideKeyboard()
            Snackbar.make(view, "Debe ingresar un nombre para el hogar y el/la enferemero/a", Snackbar.LENGTH_LONG).show()
        } else {
            val firestoreService = FirestoreService()

            view.hideKeyboard()

            firestoreService.setDocument(haven, HAVEN_COLLECTION_NAME,haven.name, object : Callback<Void>{
                override fun onSuccess(result: Void?) {
                    Snackbar.make(view,"Hogar Creado",Snackbar.LENGTH_LONG).show()

                }

                override fun onFailed(exception: Exception) {
                    showMessageError(view)
                }

            })

            Handler().postDelayed({
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1000)
        }

    }

    fun showMessageError(view : View){
        Snackbar.make(view, "El hogar no ha sido creado", Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}