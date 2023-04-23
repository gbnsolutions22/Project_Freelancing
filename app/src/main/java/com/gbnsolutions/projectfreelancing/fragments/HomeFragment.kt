package com.gbnsolutions.projectfreelancing.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gbnsolutions.projectfreelancing.R
import com.gbnsolutions.projectfreelancing.adapter.AppointmentsAdatpter
import com.gbnsolutions.projectfreelancing.model.Appointment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    lateinit var Home1: RecyclerView
    lateinit var Adapter: AppointmentsAdatpter
    lateinit var Manager: RecyclerView.LayoutManager
    lateinit var mAuth: FirebaseAuth
    lateinit var appointmentList: ArrayList<Appointment>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mAuth = FirebaseAuth.getInstance()
        Home1 = view.findViewById(R.id.home1)
        appointmentList = ArrayList()
        RetriveAppointments()
        Manager = LinearLayoutManager(activity)
        Home1.layoutManager=Manager
        return view
    }

    fun RetriveAppointments() {
        val refUsers = FirebaseDatabase.getInstance().reference.child("Appointments")
        refUsers.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                appointmentList.clear()
                for (data in snapshot.children) {
                    val appointment: Appointment? = data.getValue(Appointment::class.java)
                    if (appointment != null) {
                        if (appointment.getUID().equals(mAuth.currentUser!!.uid)) {
                            appointmentList.add(appointment)
                        }
                    }
                }
                if (appointmentList.isEmpty()){
                    appointmentList.add(Appointment("12351431242","Booked!","Slots Yet","No","https://firebasestorage.googleapis.com/v0/b/project-freelancing.appspot.com/o/domains%2Fandroid.jpg?alt=media&token=03fc4470-ab46-43b0-8781-9dc2d68cfcf7","0"))
                }
                Adapter = AppointmentsAdatpter(context as Context,appointmentList)
                Home1.adapter = Adapter
                Home1.addItemDecoration(
                    DividerItemDecoration(
                        Home1.context,
                        (Manager as LinearLayoutManager).orientation
                    )
                )
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}