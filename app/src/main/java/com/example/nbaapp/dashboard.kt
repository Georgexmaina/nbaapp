package com.example.nbaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.nbaapp.adapter.nbaAdapter
import com.example.nbaapp.model.nbaitem
import org.json.JSONException
import org.json.JSONObject

class dashboard : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val web_url = "https://www.balldontlie.io/api/v1/games"
    var nbalist:ArrayList<nbaitem> ? =  null
    var nbaAdapter : nbaAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var recyclerview : RecyclerView = findViewById(R.id.recyclerID)
        recyclerview.layoutManager = LinearLayoutManager(this)

        nbalist = ArrayList<nbaitem>()
        nbaAdapter = nbaAdapter(nbalist!!, this)
        recyclerview.adapter = nbaAdapter



        volleyRequest = Volley.newRequestQueue(this)

        fetchJsonArray(web_url)

    }

    fun fetchJsonArray(url: String) {

        val arrayRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response: JSONObject ->

                try {

                    var dataobj = response.getJSONArray("data")

                    for (x in 0 until dataobj.length()-1) {

                        var statsObj = dataobj.getJSONObject(x)

                        //var statsdate = statsObj.getString("date")

                        //Log.d("DATT", statsdate.toString())

                        for (i in 0 until statsObj.length()-1) {

                            var home_Stats_obj = statsObj.getString("home_team")

                            Log.d("DATZ", home_Stats_obj.toString())


                        }

                    }

                    //Log.d("DATAZ", dataobj.toString())

                    nbaAdapter!!.notifyDataSetChanged()


                } catch (e: JSONException) {


                }


               // Log.d("ALLDATA", dataobj.toString())


            },
            { error: VolleyError? ->
                try {
                    Log.d("Error-->", error.toString())


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            })

        arrayRequest.retryPolicy =
            DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            )


        volleyRequest!!.add(arrayRequest)
    }
}
