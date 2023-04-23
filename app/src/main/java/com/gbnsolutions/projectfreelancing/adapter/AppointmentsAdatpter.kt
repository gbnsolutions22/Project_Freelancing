package com.gbnsolutions.projectfreelancing.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gbnsolutions.projectfreelancing.R
import com.gbnsolutions.projectfreelancing.activity.AppointmentDetails
import com.gbnsolutions.projectfreelancing.model.Appointment
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AppointmentsAdatpter(val context: Context, val appointment: ArrayList<Appointment>):RecyclerView.Adapter<AppointmentsAdatpter.Appointmentsholder>() {
    class Appointmentsholder(view: View):RecyclerView.ViewHolder(view){
        val appointmentcontainer: LinearLayout = view.findViewById(R.id.appointmentcontainer)
        val title: TextView = view.findViewById(R.id.title)
        val slot: TextView = view.findViewById(R.id.slot)
        val domain: TextView = view.findViewById(R.id.domain)
        val domainpic: CircleImageView = view.findViewById(R.id.domainpic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Appointmentsholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_adapter,parent,false)
        return Appointmentsholder(view)
    }

    override fun onBindViewHolder(holder: Appointmentsholder, position: Int) {
        val appointments = appointment[position]
        holder.title.text = appointments.getTitle()
        holder.domain.text = appointments.getDomain()
        holder.slot.text = appointments.getSlot()
        Picasso.get().load(appointments.getDomainPic()).placeholder(R.drawable.ic_launcher_foreground).into(holder.domainpic)
        holder.appointmentcontainer.setOnClickListener {
            context.startActivity(Intent(context,AppointmentDetails::class.java))
        }
    }

    override fun getItemCount(): Int {
        return appointment.size
    }
}