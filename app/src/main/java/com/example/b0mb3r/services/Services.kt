package com.example.b0mb3r.services

import android.content.Context
import com.example.b0mb3r.services.GETServices.Confirmkt
import com.example.b0mb3r.services.POSTServices.Citilink
import com.example.b0mb3r.services.POSTServices.FindClone
import com.example.b0mb3r.services.POSTServices.YadnexEda

open class Services constructor(context: Context) {
    public val mapOfServices = mutableMapOf<BaseService, Boolean>(
        YadnexEda(context) to false,
        FindClone(context) to false,
        Citilink(context) to false,
        Confirmkt(context) to false
        //FindClone(context) to false,
    )


}
