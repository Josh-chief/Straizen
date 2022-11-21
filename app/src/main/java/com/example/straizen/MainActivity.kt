package com.example.straizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.stratizen.R
import com.example.stratizen.signup_page
import com.example.stratizen.user_home

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginbutton: Button = findViewById(R.id.submitBtn)
        val createAccountButton: Button = findViewById(R.id.createAccBtn)

        createAccountButton.setOnClickListener {
            val registration = Intent(this, signup_page::class.java)

            startActivity(registration)
        }

        loginbutton.setOnClickListener{
            val login = Intent(this, user_home::class.java)

            startActivity(login)
        }
    }
}