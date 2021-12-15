package com.marbey.saludasuhogar.model

import java.util.*

class Medicine {
    var name = ""
    var quantity = 0
    var dailyDose = 0
    var grandparent : String? = String()
    lateinit var dateEnd: Date
    lateinit var dateCharge: Date

}