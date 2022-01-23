package com.marbey.saludasuhogar.view.adapter

import android.view.View
import com.marbey.saludasuhogar.model.Medicine


interface MedicineListener {
    fun onMedicineClicked(grandparent: Medicine, position: Int){

    }

    fun onPlusMedicineClicked(grandparentName: String, view: View){

    }
}