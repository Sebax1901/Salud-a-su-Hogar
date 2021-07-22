package com.marbey.saludasuhogar.view.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R

class AddHavenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_haven)
    }

    fun onAddHavenClick(view: View){

        val havenName = findViewById<EditText>(R.id.havenNameAdd)
        val name = havenName.text.toString()

        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        view.hideKeyboard()

        Snackbar.make(view,"Hola $name", Snackbar.LENGTH_LONG).show()

        Handler().postDelayed({
            finish()
        }, 3000)



    }
}