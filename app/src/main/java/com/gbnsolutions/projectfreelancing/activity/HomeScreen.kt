package com.gbnsolutions.projectfreelancing.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gbnsolutions.projectfreelancing.R
import com.gbnsolutions.projectfreelancing.fragments.BookAppointmentFragment
import com.gbnsolutions.projectfreelancing.fragments.HomeFragment
import com.gbnsolutions.projectfreelancing.model.Users
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class HomeScreen : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var bottomNavigationMenu: BottomNavigationView
    lateinit var profile: CircleImageView
    lateinit var database: DatabaseReference
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigationMenu = findViewById(R.id.bottomnav)
        profile = findViewById(R.id.profile)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("Users").child(mAuth.currentUser!!.uid)
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val user: Users?= snapshot.getValue(Users::class.java)
                    Picasso.get().load(user!!.getProfile()).placeholder(R.drawable.ic_launcher_foreground).into(profile)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        profile.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        val home = HomeFragment()
        val bookAppointmentFragment = BookAppointmentFragment()
        openHome(home)
        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->openHome(home)
                R.id.book->openHome(bookAppointmentFragment)
            }
            true
        }
    }
    fun openHome(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.Container,fragment)
            commit()
        }
    }
}