package com.marbey.saludasuhogar.view.adapter

import com.marbey.saludasuhogar.model.Haven

interface HavenListener {

    fun onHavenClicked(haven: Haven, position: Int){

    }
}