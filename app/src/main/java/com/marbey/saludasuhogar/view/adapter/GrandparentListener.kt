package com.marbey.saludasuhogar.view.adapter

import android.view.View
import com.marbey.saludasuhogar.model.Grandparent

interface GrandparentListener {
    fun onGrandparentClicked(grandparent: Grandparent, position: Int){

    }

    fun onPlusGrandparentClicked(haven: String, view: View){

    }

    fun onGrandparentLongClicked(grandparent: Grandparent, view: View){

    }

}