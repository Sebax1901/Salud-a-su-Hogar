package com.marbey.saludasuhogar.view.adapter

import android.view.View
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven

interface GrandparentListener {
    fun onGrandparentClicked(grandparent: Grandparent, position: Int){

    }

    fun onPlusGrandparentClicked(haven: String, view: View){

    }

}