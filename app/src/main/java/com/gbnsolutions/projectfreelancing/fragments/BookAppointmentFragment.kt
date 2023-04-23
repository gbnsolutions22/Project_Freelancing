package com.gbnsolutions.projectfreelancing.fragments

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.gbnsolutions.projectfreelancing.R
import com.gbnsolutions.projectfreelancing.model.Domain
import com.gbnsolutions.projectfreelancing.model.Slots
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BookAppointmentFragment : Fragment() {
    lateinit var mAuth: FirebaseAuth
    lateinit var nameEditText: EditText
    lateinit var serviceSpinner: Spinner
    lateinit var slotSpinner: Spinner
    lateinit var bookButton: Button

    lateinit var database: DatabaseReference
    lateinit var databaseSave: DatabaseReference
    lateinit var databaseSlot: DatabaseReference
    lateinit var domainList: ArrayList<String>
    lateinit var domains: ArrayList<Domain>
    lateinit var slots: ArrayList<Slots>
    lateinit var slotsList: ArrayList<String>
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_book_appointment, container, false)
        mAuth = FirebaseAuth.getInstance()
        nameEditText = view.findViewById(R.id.name)
        serviceSpinner = view.findViewById(R.id.service)
        slotSpinner = view.findViewById(R.id.slot)
        bookButton = view.findViewById(R.id.bookAppointment)

        domainList = ArrayList()
        slotsList = ArrayList()
        domains = ArrayList()
        slots = ArrayList()
        database = FirebaseDatabase.getInstance().reference.child("Domains")

        database.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                domainList.clear()
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val domain: Domain ?= data.getValue(Domain::class.java)
                        domains.add(domain!!)
                        domainList.add(domain!!.getDomain().toString())
                    }
                }
                if (domainList.isEmpty()){
                    domainList.add("No Domains Available!")
                }

                val adapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,domainList) }
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                serviceSpinner.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        databaseSlot = FirebaseDatabase.getInstance().reference.child("Slots")
        databaseSlot.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                slots.clear()
                slotsList.clear()
                if (snapshot.exists()){
                    for (data in snapshot.children){
                        val slot: Slots ?= data.getValue(Slots::class.java)
                        slots.add(slot!!)
                        slotsList.add(slot.getSlot().toString())
                    }
                }
                if (slotsList.isEmpty()){
                    slotsList.add("No Slots Available!")
                }

                println(slotsList)
                val adapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,slotsList) }
                adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                slotSpinner.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // Add click listener to book button
        bookButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val service = serviceSpinner.selectedItem.toString()
            val slot = slotSpinner.selectedItem.toString()

            if (name.isEmpty() || name.equals("")){
                Toast.makeText(context,"Please enter Title of project",Toast.LENGTH_SHORT).show()
            }
            if (service.isEmpty() || service.equals("")){
                Toast.makeText(context,"Please select Domain of project",Toast.LENGTH_SHORT).show()
            }
            if (slot.isEmpty() || slot.equals("No Slots Available!") || slot.equals("")){
                Toast.makeText(context,"Please select Slot of project",Toast.LENGTH_SHORT).show()
            }
            else{
                val slotId: String = getSlotId(slots,slot).toString()
                databaseSave = FirebaseDatabase.getInstance().reference
                val hashMap = hashMapOf<String,Any>()
                hashMap["title"] = name
                hashMap["uid"] = mAuth.currentUser!!.uid
                hashMap["slot"] = slot
                hashMap["domain"] = service
                hashMap["domainpic"] = getDomainPic(domains, service).toString()
                hashMap["slotid"] = slotId
                val randomKey: String? = databaseSave.push().getKey()
                databaseSave.child("Appointments").child(randomKey!!).updateChildren(hashMap)
                databaseSave.child("Slots").child(slotId).removeValue()

                // Do something with the booking details
                Toast.makeText(context, "Booking confirmed for $name for $service on $slot", Toast.LENGTH_LONG).show()
                val nextFragment = HomeFragment()
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.Container, nextFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        return view
    }
    fun getDomainPic(domains: ArrayList<Domain>, selectedDomain: String): String? {
        for (domain in domains) {
            if (domain.getDomain() == selectedDomain) {
                return domain.getDomainPic()
            }
        }
        return null
    }
    fun getSlotId(slots: ArrayList<Slots>, selectedSlot: String): String? {
        for (slot in slots) {
            if (slot.getSlot() == selectedSlot) {
                return slot.getSlotId()
            }
        }
        return null
    }
}