package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import org.json.JSONObject

class Citilink(context: Context) : POSTService(context) {
    override val baseUrl: String = "https://www.citilink.ru/registration/confirm/phone/+79278214538/"
    override val serviceName: String
        get() = "Citilink"

    override fun execute(phoneNumber: String) {
        super.execute(phoneNumber = phoneNumber)


        val json = JSONObject("""{"phone": {"code":1, "number":$phoneNumber}} """)
        Log.d("Requset", "Json is ${json.toString()}")

        makeRequest(json)

    }


}