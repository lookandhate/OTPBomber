package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import org.json.JSONObject

class YadnexEda(context: Context) : BaseService(context) {
    override val baseUrl: String = "https://eda.yandex/api/v1/user/request_authentication_code"
    override val serviceName: String
        get() = "YandexEda"

    override fun execute(phoneNumber: String) {
        super.execute(phoneNumber = phoneNumber)

        val jsonMap = hashMapOf<String, String>()
        jsonMap.put("phone_number", phoneNumber)

        val json = JSONObject(jsonMap as Map<String, String>)
        Log.d("Requset", "Json is ${json.toString()}")


        makeRequest(json)

    }


}