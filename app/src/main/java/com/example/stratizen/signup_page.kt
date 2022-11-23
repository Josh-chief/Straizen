package com.example.stratizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.straizen.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signup_page : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
         var createAccont: Button = findViewById(R.id.btnCreate)
         var alreadyUser: TextView = findViewById(R.id.userAlready)



        auth = Firebase.auth



        createAccont.setOnClickListener {
            create_account()
        }

        alreadyUser.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun create_account() {

        var username: TextInputLayout =findViewById(R.id.userName)
        var studentNo: TextInputLayout = findViewById(R.id.studentNo)
        var email: TextInputLayout = findViewById(R.id.userEmail)
        var password: TextInputLayout = findViewById(R.id.userPassword)

        val inputUsername=  username.editText?.text.toString()
        val inputStudentNumber = studentNo.editText?.text.toString()
        val inputEmail =  email.editText?.text.toString()
        val inputPassword = password.editText?.text.toString()


        if(inputUsername.isEmpty() || inputStudentNumber.isEmpty()||inputEmail.isEmpty() || inputPassword.isEmpty()){
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_LONG).show()
        }else{
            var login = Intent(this, MainActivity::class.java)
            startActivity(login)
        }
    }


}
