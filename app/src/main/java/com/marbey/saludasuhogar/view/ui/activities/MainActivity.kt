package com.marbey.saludasuhogar.view.ui.activities


import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.view.adapter.CreateHavenListener
import com.marbey.saludasuhogar.view.ui.fragments.AddHavenDialogFragment

class MainActivity : FragmentActivity(), CreateHavenListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.tbMain))

    }

    fun onPlusClicked(view: View) {
        val newFragment = AddHavenDialogFragment()
        newFragment.show(supportFragmentManager, "Hola Mundo")
    }

    override fun createHaven(view: View) {
        super.createHaven(view)
    }


}