package com.marbey.saludasuhogar.view.adapter

import com.marbey.saludasuhogar.model.Medicine


interface MedicineListener {
    fun onMedicineClicked(granparent: Medicine, position: Int){

    }
}