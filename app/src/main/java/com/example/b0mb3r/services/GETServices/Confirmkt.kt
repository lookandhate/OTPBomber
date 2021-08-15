package com.example.b0mb3r.services.GETServices

import android.content.Context
import android.util.Log
import com.example.b0mb3r.services.GETService

class Confirmkt(context: Context) : GETService(context) {
    override val baseUrl: String = "https://securedapi.confirmtkt.com/api/platform/register"
    override val serviceName: String
        get() = "Confirmkt"

    override fun execute(phoneNumber: String) {
        super.execute(phoneNumber)
        val finalURL = "$baseUrl?newOtp=true&mobileNumber=$phoneNumber"
        makeRequest(finalURL)

    }
}