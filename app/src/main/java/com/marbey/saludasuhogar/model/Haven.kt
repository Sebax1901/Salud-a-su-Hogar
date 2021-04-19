package com.marbey.saludasuhogar.model

import java.io.Serializable

class Haven: Serializable {
    var name = ""
    var nurseName = ""
    var image = ""
    var granparentList: List<Grandparent>? = null

}