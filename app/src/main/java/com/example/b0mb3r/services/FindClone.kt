package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import org.json.JSONObject

class FindClone(context: Context) : BaseService(context) {
    override val baseUrl: String = "https://findclone.ru/register"
    override val serviceName: String
        get() = "FindClone"

    override fun execute(phoneNumber: String) {
        super.execute(phoneNumber = phoneNumber)

        val jsonMap = hashMapOf<String, String>()
        jsonMap.put("phone", phoneNumber)

        val json = JSONObject(jsonMap as Map<String, String>)
        Log.d("Requset", "Json is ${json.toString()}")

        makeRequest(json)

    }


}