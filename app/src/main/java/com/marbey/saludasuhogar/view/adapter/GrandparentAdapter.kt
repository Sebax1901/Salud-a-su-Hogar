package com.marbey.saludasuhogar.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent

class GrandparentAdapter(val grandparentListener: GranparentListener) : RecyclerView.Adapter<GrandparentAdapter.ViewHolder>(){

    var listGranparent = ArrayList<Grandparent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_grandparents, parent, false))

    override fun onBindViewHolder(holder: GrandparentAdapter.ViewHolder, position: Int) {
        val grandparent = listGranparent[position] as Grandparent

        holder.tvGrandparentName.text = grandparent.name
        holder.tvGrandparentAge.text = grandparent.age.toString()
        holder.tvGrandparentHaven.text = grandparent.haven

        holder.itemView.setOnClickListener {
            grandparentListener.onGranparentClicked(grandparent, position)
        }

    }

    override fun getItemCount() = listGranparent.size

    fun updateData(data: List<Grandparent>){
        listGranparent.clear()
        listGranparent.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvGrandparentName = itemView.findViewById<TextView>(R.id.tvGranparentName)!!
        val tvGrandparentAge = itemView.findViewById<TextView>(R.id.tvGranparentAge)!!
        val tvGrandparentHaven = itemView.findViewById<TextView>(R.id.tvGranparentHaven)!!
    }

}
