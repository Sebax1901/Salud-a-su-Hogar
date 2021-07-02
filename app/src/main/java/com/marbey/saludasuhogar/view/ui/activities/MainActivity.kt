package com.marbey.saludasuhogar.view.ui.activities


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.view.adapter.CreateHavenListener
import com.marbey.saludasuhogar.view.ui.fragments.AddGrandparentDialogFragment
import com.marbey.saludasuhogar.view.ui.fragments.AddHavenDialogFragment
import kotlinx.android.synthetic.main.fragment_add_haven_dialog.*
import kotlinx.android.synthetic.main.fragment_haven.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tbMain))

    }

    fun onPlusHavenClicked(view: View) {
        val newFragment = AddHavenDialogFragment()
        newFragment.show(supportFragmentManager, "OnPlusHavenFragment")
    }

    fun onPlusGrandparentClicked(view: View) {
        val newFragment = AddGrandparentDialogFragment()
        newFragment.show(supportFragmentManager, "OnPlusHavenFragment")
    }

    fun onAddHavenClick(view: View){

        val addHaven = AddHavenDialogFragment()
        addHaven.createHaven(view)

    }



}