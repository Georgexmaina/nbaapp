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

                    for (i in 0 until dataobj.length() - 1) {

                        var alldata = dataobj.getJSONObject(i)

                        var date = alldata.getString("date").toInt()
                        var hometeam = alldata.getString("home_team")
                        var awayteam = alldata.getString("visitor_team")
                        var goalshome = alldata.getString("home_team_score").toInt()
                        var goalsaway = alldata.getString("visitor_team_score").toInt()


                        var news = nbaitem()
                        news.date = date
                        news.hometeam = hometeam
                        news.awayteam = awayteam
                        news.goalsHome = goalshome
                        news.goalsAway = goalsaway


                        nbalist!!.add(news)

                    }

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
