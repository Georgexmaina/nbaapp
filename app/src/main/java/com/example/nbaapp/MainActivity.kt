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

class MainActivity : AppCompatActivity() {

    private lateinit var edtusername: EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var btnlogin: Button
    private var mAuth: FirebaseAuth? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtusername = findViewById(R.id.edt_username)
        edtemail = findViewById(R.id.edt_email)
        edtpassword = findViewById(R.id.edt_password)
        btnsignup = findViewById(R.id.btn_signup)
        btnlogin = findViewById(R.id.btn_login)

        mAuth = FirebaseAuth.getInstance()

        btnsignup.setOnClickListener {
            var email = edtemail.text.toString().trim()
            var password = edtpassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "Empty Fields" , Toast.LENGTH_SHORT).show()
            } else {

                mAuth!!.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task: Task<AuthResult> ->
                        if (task.isSuccessful) {

                            Log.d("Create--->", task.toString())

                            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                            var loginIntent = Intent(this, login::class.java)
                            startActivity(loginIntent)
                            finish()
                        } else {

                            // Log.d("Error--->", task.toString())

                            Toast.makeText(this, "Failed to Create Account", Toast.LENGTH_SHORT).show()
                            Log.d("Error==>", task.exception.toString())
                        }
                        btnlogin.setOnClickListener {
                            Toast.makeText(this,"Proceeding To Next Page", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this, login::class.java)
                            startActivity(intent)
                        }

                    }

            }


        }
    }
}