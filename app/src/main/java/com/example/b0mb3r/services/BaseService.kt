package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

open class BaseService(context: Context) : ServiceAbstractClass(context) {
    override val baseUrl: String
        get() = TODO("Not yet implemented")
    override val serviceName: String
        get() = TODO("Not yet implemented")

    override fun execute(phoneNumber: String) {
        Log.d(LogTag, "$serviceName executed")
    }


}
