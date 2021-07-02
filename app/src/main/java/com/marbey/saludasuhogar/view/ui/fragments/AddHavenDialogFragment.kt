package com.marbey.saludasuhogar.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.view.adapter.CreateHavenListener
import kotlinx.android.synthetic.main.fragment_add_haven_dialog.*

open class AddHavenDialogFragment : DialogFragment(), CreateHavenListener {

    val firestoreService = FirestoreService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_haven_dialog, container, false)
    }

    override fun createHaven(view: View) {
        super.createHaven(view)
        val nurseName = nurseNameAdd.text.toString()
        Snackbar.make(view,nurseName,50).show()
    }


}