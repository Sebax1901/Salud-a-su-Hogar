package com.marbey.saludasuhogar.view.ui.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.marbey.saludasuhogar.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tbMain))
    }

    fun onPlusHavenClicked(view: View) {
        val intent = Intent(this, AddHavenActivity::class.java)
        startActivity(intent)
    }

    fun onPlusGrandparentClicked(view: View) {
        val intent = Intent(this, AddGrandparentActivity::class.java)
        startActivity(intent)
    }





}