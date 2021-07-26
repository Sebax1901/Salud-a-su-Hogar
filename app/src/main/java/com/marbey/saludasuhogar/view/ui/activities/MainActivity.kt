package com.marbey.saludasuhogar.view.ui.activities


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.marbey.saludasuhogar.R
import kotlinx.android.synthetic.main.activity_main.*

enum class ProviderType{
    BASIC
}

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tbMain))
        tbMain.setNavigationOnClickListener {
            FirebaseAuth.getInstance().signOut()

            // Borrado de datos

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            // Cierre de la actividad

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Save Signin Data

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()


    }

    fun onPlusHavenClicked(view: View) {
        val intent = Intent(this, AddHavenActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onPlusGrandparentClicked(view: View) {
        val intent = Intent(this, AddGrandparentActivity::class.java)
        startActivity(intent)

    }





}