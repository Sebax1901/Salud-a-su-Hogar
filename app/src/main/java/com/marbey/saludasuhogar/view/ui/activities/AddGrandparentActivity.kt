package com.marbey.saludasuhogar.view.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R

class AddGrandparentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_grandparent)
    }

    fun onAddGrandparentClick(view: View){

        val grandparentName = findViewById<EditText>(R.id.GrandparentNameAdd)
        val name = grandparentName.text.toString()
        val grandparentAge = findViewById<EditText>(R.id.grandParentAgeAdd)
        val age = grandparentAge.text.toString()

        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        view.hideKeyboard()

        Snackbar.make(view,"Creando el paciente $name con edad $age", Snackbar.LENGTH_LONG).show()

        Handler().postDelayed({
            finish()
        }, 3000)

    }
}