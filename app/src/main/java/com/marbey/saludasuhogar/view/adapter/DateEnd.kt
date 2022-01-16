package com.marbey.saludasuhogar.view.adapter

import java.text.SimpleDateFormat
import java.util.*

interface DateEnd {

    fun daysPassed(day: Int, month: Int, year: Int) : Long {

        val date : Date? = SimpleDateFormat("dd/MM/yyyy").parse("$day/$month/$year")
        val actualDate = Date(System.currentTimeMillis())
        var daysPassed = actualDate.time - date!!.time
        daysPassed = (daysPassed / 1000 / 60 / 60 / 24)

        return daysPassed

    }

}