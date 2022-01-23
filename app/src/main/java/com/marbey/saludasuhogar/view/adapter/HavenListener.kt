package com.marbey.saludasuhogar.view.adapter

import android.view.View
import com.marbey.saludasuhogar.model.Haven

interface HavenListener {

    fun onHavenClicked(haven: Haven, position: Int){

    }

    fun onHavenLongClicked(haven: Haven, view: View){

    }


}