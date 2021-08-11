package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class GETService(context: Context) : BaseService(context) {


    fun makeRequest(urlToSendRequestTo: String) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, urlToSendRequestTo, JSONObject(),
            { response ->
                Log.d("Request", "Response: $response")
            },
            { error ->
                Log.e(
                    "Request", "Error occured: ${error.networkResponse.toString()}} \n" +
                            "Stack trace: ${error.stackTrace.asList().toString()}"
                )
            }
        )

        // Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }
}