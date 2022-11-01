package com.example.nbaapp.model

class nbaitem() {


    var date:Int? = null
    var hometeam:String ? = null
    var awayteam:String ? = null
    var goalsHome:Int? = null
    var goalsAway:Int? = null



    constructor(date:Int, hometeam:String, awayteam:String, goalsHome:Int, goalsAway:Int) : this() {

        this.date = date
        this.hometeam = hometeam
        this.awayteam = awayteam
        this.goalsHome= goalsHome
        this.goalsAway = goalsAway



    }
}


