package com.marbey.saludasuhogar.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.view.ui.fragments.HomeFragment

class HavenAdapter(val havenListener: HomeFragment) : RecyclerView.Adapter<HavenAdapter.ViewHolder>(){

    var listHaven = ArrayList<Haven>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HavenAdapter.ViewHolder, position: Int) {
        val haven = listHaven[position] as Haven

        holder.tvHavenName.text = "Hogar: " + haven.name
        holder.tvNurseName.text = "Jefe: " + haven.nurseName

        holder.itemView.setOnClickListener {
            havenListener.onHavenClicked(haven, position)
        }

        holder.itemView.setOnLongClickListener {
            havenListener.onHavenLongClicked(haven,it)
            return@setOnLongClickListener true
        }

    }

    override fun getItemCount() = listHaven.size

    fun updateData(data: List<Haven>){
        listHaven.clear()
        listHaven.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvHavenName = itemView.findViewById<TextView>(R.id.tvItemHomeDetail)
        val tvNurseName = itemView.findViewById<TextView>(R.id.tvItemNombreJefe)

    }

}
