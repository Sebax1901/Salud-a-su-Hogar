package com.marbey.saludasuhogar.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent

class GrandparentAdapter(val grandparentListener: GrandparentListener) : RecyclerView.Adapter<GrandparentAdapter.ViewHolder>(){

    var listGrandparent = ArrayList<Grandparent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grandparents, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GrandparentAdapter.ViewHolder, position: Int) {
        val grandparent = listGrandparent[position] as Grandparent

        holder.tvGrandparentName.text = "Nombre: " + grandparent.name
        holder.tvGrandparentAge.text = "Edad: " + grandparent.age.toString()
        holder.tvGrandparentHaven.text = "Hogar: " + grandparent.haven

        holder.itemView.setOnClickListener {
            grandparentListener.onGrandparentClicked(grandparent, position)
        }

        holder.itemView.setOnLongClickListener {
            grandparentListener.onGrandparentLongClicked(grandparent,it)
            return@setOnLongClickListener true
        }

    }

    override fun getItemCount() = listGrandparent.size

    fun updateData(data: List<Grandparent>){
        listGrandparent.clear()
        listGrandparent.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvGrandparentName = itemView.findViewById<TextView>(R.id.tvGrandparentName)!!
        val tvGrandparentAge = itemView.findViewById<TextView>(R.id.tvGrandparentAge)!!
        val tvGrandparentHaven = itemView.findViewById<TextView>(R.id.tvGrandparentHaven)!!
    }

}
