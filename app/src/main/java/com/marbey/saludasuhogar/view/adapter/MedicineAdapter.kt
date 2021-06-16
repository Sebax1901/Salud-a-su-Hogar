package com.marbey.saludasuhogar.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Medicine
import java.text.SimpleDateFormat

class MedicineAdapter(val medicineListener: MedicineListener) : RecyclerView.Adapter<MedicineAdapter.ViewHolder>(){

    var listMedicine = ArrayList<Medicine>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_medicine, parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = listMedicine[position] as Medicine

        holder.tvMedicineName.text = medicine.name
        val pattern = "dd//MM/yyyy "
        val simpleDF = SimpleDateFormat(pattern)
        val dateCharge = simpleDF.format(medicine.dateCharge)
        val dateEnd = simpleDF.format(medicine.dateEnd)
        holder.tvChargeDate.text = "Fecha de Carga: $dateCharge"
        holder.tvEndDate.text = "Fecha de Culminación: $dateEnd"
        holder.tvMedicineDose.text = "Dosis Diaria: " + medicine.dailyDose.toString()
        holder.tvMedicineQuantity.text = "Cantidad: " + medicine.quantity.toString()

    }

    override fun getItemCount() = listMedicine.size

    fun updateData(data: List<Medicine>){
        listMedicine.clear()
        listMedicine.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvMedicineName = itemView.findViewById<TextView>(R.id.tvDetailGrandParentMedicine)!!
        val tvChargeDate = itemView.findViewById<TextView>(R.id.tvDetailChargeDate)!!
        val tvMedicineQuantity = itemView.findViewById<TextView>(R.id.tvDetailMedicineQuantity)!!
        val tvMedicineDose = itemView.findViewById<TextView>(R.id.tvDetailMedicineDose)!!
        val tvEndDate = itemView.findViewById<TextView>(R.id.tvDetailEndDate)!!

    }

}