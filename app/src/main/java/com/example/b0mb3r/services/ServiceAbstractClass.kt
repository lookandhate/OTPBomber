package com.example.b0mb3r.services

import android.content.Context
import com.android.volley.toolbox.Volley
import java.util.*

abstract class ServiceAbstractClass constructor(context: Context) {

    val queue = Volley.newRequestQueue(context)
    val LogTag = "Services"


    abstract val baseUrl: String;
    abstract val serviceName: String;
    abstract fun execute(phoneNumber: String);
}