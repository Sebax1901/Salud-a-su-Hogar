package com.marbey.saludasuhogar.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Medicine

class MedicineAdapter(val medicineListener: MedicineListener) : RecyclerView.Adapter<MedicineAdapter.ViewHolder>(), DateEnd{

    var listMedicine = ArrayList<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = listMedicine[position] as Medicine

        val day = medicine.dayCharge
        val month = medicine.monthCharge
        val year = medicine.yearCharge

        var days = daysPassed(day,month,year).toInt()

        medicine.actualQuantity = getQuantity(medicine, days, medicine.dailyDose)

        holder.tvMedicineName.text = medicine.name

        holder.tvChargeDate.text = "Fecha de Carga:\n ${day}/${month}/${year}"
        holder.tvEndDate.text = "Loren Itsum" //dateEnd(day,month,year).toString()
        holder.tvMedicineDose.text = "Dosis Diaria: " + medicine.dailyDose.toString()
        holder.tvMedicineQuantity.text = "Cantidad: " + medicine.actualQuantity.toString()

    }

    override fun getItemCount() = listMedicine.size

    fun updateData(data: List<Medicine>){
        listMedicine.clear()
        listMedicine.addAll(data)
        notifyDataSetChanged()
    }

    fun getQuantity(medicine : Medicine, days : Int, convFactor : Double) : Double{
        var quantity = medicine.quantity - (days * convFactor)
        return quantity
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvMedicineName = itemView.findViewById<TextView>(R.id.tvDetailGrandParentMedicine)!!
        val tvChargeDate = itemView.findViewById<TextView>(R.id.tvDetailChargeDate)!!
        val tvMedicineQuantity = itemView.findViewById<TextView>(R.id.tvDetailMedicineQuantity)!!
        val tvMedicineDose = itemView.findViewById<TextView>(R.id.tvDetailMedicineDose)!!
        val tvEndDate = itemView.findViewById<TextView>(R.id.tvDetailEndDate)!!

    }

}