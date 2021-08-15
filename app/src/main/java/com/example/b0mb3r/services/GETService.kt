package com.example.b0mb3r.services

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import org.json.JSONObject
import kotlin.math.max
import kotlin.math.min

open class GETService(context: Context) : BaseService(context) {
    override val LogTag = "GETService"

    fun makeRequest(urlToSendRequestTo: String) {
        Log.d("GETService", "Performing request to $urlToSendRequestTo")
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, urlToSendRequestTo,
            { response ->
                // Display the first 500 characters of the response string.
                Log.d(LogTag, "Response is: ${response.substring(0, min(500, response.length - 1))}")
            },
            { error ->

                Log.e(LogTag, "Error occured: ${error.networkResponse}")
            }
        )

        // Access the RequestQueue through your singleton class.
        queue.add(stringRequest)
    }
}