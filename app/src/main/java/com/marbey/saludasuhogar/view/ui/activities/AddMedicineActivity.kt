package com.marbey.saludasuhogar.view.ui.activities

import android.app.DatePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.network.Callback
import com.marbey.saludasuhogar.network.FirestoreService
import com.marbey.saludasuhogar.network.GRANDPARENTS_COLLECTION_NAME
import com.marbey.saludasuhogar.network.MEDICINE_COLLECTION_NAME
import com.marbey.saludasuhogar.view.ui.fragments.DatePickerFragment
import com.marbey.saludasuhogar.view.ui.fragments.HavenFragment
import kotlinx.android.synthetic.main.activity_add_medicine.*
import java.lang.Exception
import java.util.*

class AddMedicineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_medicine)

        val grandparentName = intent.extras?.getString("grandParentName")

        buttonAddMedicine.setOnClickListener {
            onAddMedicineClick(grandparentName, it)
        }

        ChargeDateAdd.setOnClickListener { openCalendar() }

    }

    private fun openCalendar(){
        val datePicker = DatePickerFragment {day, month, year -> onDateSelected(day, month, year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int){
        ChargeDateAdd.setText("$day / $month / $year")
    }

    private fun onAddMedicineClick(grandParentName: String?, view: View){

        val medicine = Medicine()

        medicine.name = findViewById<EditText>(R.id.medicineNameAdd).text.toString()
        val quantity = findViewById<EditText>(R.id.quantityAdd).text.toString()
        medicine.grandparent = grandParentName
        val dailyDose = findViewById<EditText>(R.id.dailyDoseAdd).text.toString()
        medicine.quantity = quantity.toInt()
        medicine.dailyDose = dailyDose.toInt()


        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        view.hideKeyboard()

        Snackbar.make(view,"Creando la medicina ${medicine.name} con:\n" +
                "cantidad: ${medicine.quantity}\n" +
                "Paciente: ${medicine.grandparent}\n" +
                "Dosis Diaria: ${medicine.dailyDose}", Snackbar.LENGTH_LONG).show()

    }

    fun showMessageError(view : View){
        Snackbar.make(view, "La Medicina no ha sido creada", Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}