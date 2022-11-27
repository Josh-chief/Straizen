package com.example.straizen

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stratizen.Password_Reset_Activity
import com.example.stratizen.R
import com.example.stratizen.signup_page
import com.example.stratizen.user_home
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
//    public val settings = getSharedPreferences("mySharedPref", 0)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get auth instance
        auth = Firebase.auth


    }

    override fun onStart() {
        super.onStart()

        //Object declaration
        val loginbutton: Button = findViewById(R.id.submitBtn)
        val createAccountButton: Button = findViewById(R.id.createAccBtn)
        val forgotPassword: TextView = findViewById(R.id.forgotPassword)

        //Check if a user is signed in
        val currentUser = auth.currentUser


        if(currentUser != null){
            //if user is signed in take to dashboard
            var intent = Intent(this, user_home::class.java)
            startActivity(intent)
        }else{

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






    }


    public fun login() {


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

//                        val editor: SharedPreferences.Editor = settings.edit()
//                        editor.putBoolean("connected", true)
//                        editor.commit()

                        startActivity(login)

                    } else {

                        Toast.makeText(baseContext, "Sign in failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }


    }


}