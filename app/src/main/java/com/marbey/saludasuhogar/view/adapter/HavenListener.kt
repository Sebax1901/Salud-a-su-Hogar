package com.marbey.saludasuhogar.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Haven

interface HavenListener {

    fun onHavenClicked(haven: Haven, position: Int){

    }

    fun onHavenLongClicked(haven: Haven, view: View){

    }


}