package com.example.stratizen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Password_Reset_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)


        val resetspasswordBtn: Button = findViewById(R.id.resetPasswordBtn)


        resetspasswordBtn.setOnClickListener {
            passwordReset()
        }

    }

    private fun passwordReset() {

        val resetEmail:TextInputLayout = findViewById(R.id.userResetEmail)
        val emailAddress = resetEmail.editText?.text.toString().trim{it <= ' '}

        if(emailAddress.isEmpty()){
            Toast.makeText(this, "Field is empty", Toast.LENGTH_SHORT).show()
        }else{
            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Reset password link sent to email", Toast.LENGTH_SHORT).show()

//                        val intent = Intent(Intent.ACTION_MAIN)
//                        intent.addCategory(Intent.CATEGORY_APP_EMAIL)
//                        startActivity(intent)

                    }else{
                        Toast.makeText(this, "Email does not exist", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}