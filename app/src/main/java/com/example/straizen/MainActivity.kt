package com.example.straizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginbutton: Button = findViewById(R.id.login_button)

        loginbutton.setOnClickListener {
            val login = Intent(this, Dashboard::class.java )
            startActivity(login)
        }
    }
}