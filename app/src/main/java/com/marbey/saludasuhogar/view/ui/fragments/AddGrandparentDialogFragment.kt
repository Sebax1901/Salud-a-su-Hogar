package com.marbey.saludasuhogar.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.network.FirestoreService


class AddGrandparentDialogFragment : DialogFragment() {

    val firestoreService = FirestoreService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_grandparent_dialog, container, false)
    }

    fun CreateGrandparent(view: View){
        //firestoreService.setDocument
    }


}