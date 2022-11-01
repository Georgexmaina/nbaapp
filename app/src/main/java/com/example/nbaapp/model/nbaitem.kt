package com.example.nbaapp.model

class nbaitem() {


    var date:Int? = null
    var hometeam:String ? = null

    var goalsHome:Int? = null




    constructor(date:Int, hometeam:String, awayteam:String, goalsHome:Int, goalsAway:Int) : this() {

        this.date = date
        this.hometeam = hometeam

        this.goalsHome= goalsHome




    }
}


