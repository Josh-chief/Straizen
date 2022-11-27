package com.example.stratizen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.straizen.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

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

            if(inputPassword.length<8){
                Toast.makeText(this, "Password has to be 8 character or more", Toast.LENGTH_LONG).show()
            }else{
                auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val database = Firebase.database
                            val myRef = database.getReference("User")

                            var user: User


                            var userIDList = mutableListOf<String>()

                            val currentuser = FirebaseAuth.getInstance().currentUser!!
                                .uid


                            userIDList.add(currentuser)

                            user = User(currentuser,inputUsername,inputStudentNumber,inputEmail)

                            myRef.addValueEventListener(object: ValueEventListener{

                                override fun onDataChange(snapshot: DataSnapshot) {
                                    myRef.child("users").child(currentuser).setValue(user)

                                    username.editText?.text?.clear()
                                    studentNo.editText?.text?.clear()
                                    email.editText?.text?.clear()
                                    password.editText?.text?.clear()

                                    finish()
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(baseContext, "Data store Failed",
                                        Toast.LENGTH_SHORT).show()
                                }
                            })






                        } else {
                            Toast.makeText(baseContext, "Registration failed",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }


        }
    }


}
