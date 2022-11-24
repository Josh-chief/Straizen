package com.example.straizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.stratizen.Password_Reset_Activity
import com.example.stratizen.R
import com.example.stratizen.signup_page
import com.example.stratizen.user_home
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val loginbutton: Button = findViewById(R.id.submitBtn)
        val createAccountButton: Button = findViewById(R.id.createAccBtn)
        val forgotPassword: TextView = findViewById(R.id.forgotPassword)






        loginbutton.setOnClickListener{

            login()

        }

        forgotPassword.setOnClickListener {
            var intent = Intent(this, Password_Reset_Activity::class.java)
            startActivity(intent)
        }


        createAccountButton.setOnClickListener {
            var intent = Intent(this, signup_page::class.java)
            startActivity(intent)

        }
    }


    private fun login() {


        var email: TextInputLayout =findViewById(R.id.studentID)
        var password: TextInputLayout = findViewById(R.id.userPwd)

        val inputEmail =  email.editText?.text.toString()
        val inputPassword = password.editText?.text.toString()


        if(inputEmail.isEmpty() || inputPassword.isEmpty()){
            Toast.makeText(this, "Fill all fields",Toast.LENGTH_LONG).show()
        }else{


            auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(baseContext, "Sign in Successful", Toast.LENGTH_SHORT).show()
                        var login = Intent(this, user_home::class.java)
                        startActivity(login)

                    } else {

                        Toast.makeText(baseContext, "Sign in failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }


    }


}