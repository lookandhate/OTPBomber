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

    fun makeRequest(json: JSONObject) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, baseUrl, json,
            { response ->
                Log.d("Request", "Response: $response")
            },
            { error ->
                Log.e("Request", "Error occured: ${error.networkResponse.toString()}} \n" +
                        "Stack trace: ${error.stackTrace.asList().toString()}")
            }
        )

        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }

}
