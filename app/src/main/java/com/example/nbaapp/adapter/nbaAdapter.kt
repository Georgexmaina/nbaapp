package com.example.nbaapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nbaapp.R
import com.example.nbaapp.model.nbaitem

class nbaAdapter(private val list: ArrayList<nbaitem>, private val context : Context): RecyclerView.Adapter<nbaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        //inflate our layout
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //check if its null
        holder.bindViews(list[position])


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //fetch all of our views
        var txt_date = itemView.findViewById<TextView>(R.id.txtDate)
        var txt_hometeam = itemView.findViewById<TextView>(R.id.txt_homeTeam)
        var txt_awayteam = itemView.findViewById<TextView>(R.id.txt_visitTeam)
        var txt_goalshome = itemView.findViewById<TextView>(R.id.txtgoalsHome)
        var txt_goalsaway = itemView.findViewById<TextView>(R.id.txtgoalsAway)


        fun bindViews(news_item: nbaitem) {
            txt_date.text = news_item.date.toString()
            txt_hometeam.text = news_item.hometeam
            txt_awayteam.text = news_item.awayteam
            txt_goalshome.text = news_item.goalsHome.toString()
            txt_goalsaway.text = news_item.goalsAway.toString()

        }
    }
}