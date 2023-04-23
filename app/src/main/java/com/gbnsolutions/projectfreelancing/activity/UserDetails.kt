package com.gbnsolutions.projectfreelancing.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.gbnsolutions.projectfreelancing.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserDetails : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var email: EditText
    lateinit var submit: Button
    lateinit var male: CheckBox
    lateinit var female: CheckBox

    private var gender: String = ""
    private var userName: String = ""
    private var Email: String = ""

    private lateinit var database: DatabaseReference
    lateinit var firebaseUserID: String
    lateinit var phoneNumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        name = findViewById(R.id.username)
        email = findViewById(R.id.email)
        submit = findViewById(R.id.submit)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)

        submit.setOnClickListener {
            if (male.isChecked){
                gender="Male"
                female.isChecked=false
            }
            if (female.isChecked){
                gender="Female"
                male.isChecked=false
            }
            if (!name.text.equals("") && !email.text.equals("")) {
                userName = name.text.toString()
                Email = email.text.toString()
                if (intent!=null){
                    firebaseUserID = intent.getStringExtra("uid").toString()
                    phoneNumber = intent.getStringExtra("phoneNumber").toString()
                    database = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["UID"] = firebaseUserID
                    userHashMap["PhoneNumber"] = phoneNumber
                    userHashMap["Gender"] = gender
                    userHashMap["Name"] = userName
                    userHashMap["Email"] = Email
                    userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/gbn-avengers.appspot.com/o/Login%2Fic_profile.png?alt=media&token=7ca935a8-3409-48eb-a425-37aced50b88f"
                    database.updateChildren(userHashMap)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful){
                                Toast.makeText(this,"User Registered successfully!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this,"Something went wrong!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    startActivity(Intent(this,HomeScreen::class.java))
                    finish()
                }
            }
        }
    }
}