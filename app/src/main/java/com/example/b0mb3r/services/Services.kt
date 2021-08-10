package com.example.b0mb3r.services

import android.content.Context

open class Services constructor(context: Context) {
    public val mapOfServices = mutableMapOf<BaseService, Boolean>(
        YadnexEda(context) to false,
        FindClone(context) to false,
        Citilink(context) to false,
        //FindClone(context) to false,
    )


}
