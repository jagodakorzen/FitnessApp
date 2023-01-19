package com.example.fitnessapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }


    /*
        val query = "SELECT users.id, users.username, roles.role FROM `users` INNER JOIN roles ON users.role_id = roles.id WHERE users.username='${MainActivity.username}'"
        qResult = null
        queryDB(MainActivity.username,MainActivity.password,query)
        val user_result = qResult

        Handler(Looper.getMainLooper()).postDelayed({
            //Do something after 1000ms
            val tv = findViewById<TextView>(R.id.textView)
            if (qResult==null) {
                tv.text = "Cos poszlo nie tak!"
            }
            else {
                tv.text = "Jestes zalogowany do sklepu jako \n"+qResult.toString()
            }
        }, 1000)*/

    fun onClickQuery(v: View) {

        val user = MainActivity.username
        val password = MainActivity.password
        val query = findViewById<EditText>(R.id.editTextQuery).text.toString()

        val url = "http://10.0.2.2/androiddb/"


        // Post parameters
        val jsonObject = JSONObject()
        jsonObject.put("username", user)
        jsonObject.put("password", password)
        jsonObject.put("email", "")
        jsonObject.put("query", query)

        Log.d("fun onClickQuery:", "jsonObject: $jsonObject")

        // Volley post request with parameters
        val requestPOST = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                // Process the json
                try {

                    Log.d("fun onClickQuery:", "Response: $response")
                    printResult(response)
                } catch (e: Exception) {
                    Log.d("fun onClickQuery:", "Exception: $e")
                }

            }, Response.ErrorListener {
                // Error in request
                Log.d("fun onClickQuery:", "Volley error: $it")
            })

        VolleySingleton.getInstance(this).addToRequestQueue(requestPOST)

    }

    fun printResult(result: JSONObject) {
        findViewById<TextView>(R.id.resultTextView).text = result["message"].toString()
    }
}




