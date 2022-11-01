package com.example.nbaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    private lateinit var logemail : EditText
    private lateinit var logpassword : EditText
    private lateinit var btnlogin : Button
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        logemail = findViewById(R.id.log_email)
        logpassword = findViewById(R.id.log_password)
        btnlogin = findViewById(R.id.btn_login)

        mAuth = FirebaseAuth.getInstance()

        btnlogin.setOnClickListener {

            var useremail = logemail.text.toString().trim()
            var userpass = logpassword.text.toString().trim()

            mAuth!!.signInWithEmailAndPassword(useremail, userpass).addOnCompleteListener {

                    task: Task<AuthResult> ->

                if (task.isSuccessful){


                    Toast.makeText(this, "LOGIN SUCCESS", Toast.LENGTH_SHORT).show()
                    var intent = Intent(this, dashboard::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "LOGIN FAILED", Toast.LENGTH_SHORT).show()
                    Log.d("error-->",task.exception.toString())

                }
            }

        }
    }
}