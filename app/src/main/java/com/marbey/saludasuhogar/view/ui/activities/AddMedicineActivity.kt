package com.marbey.saludasuhogar.view.ui.activities

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.view.ui.fragments.DatePickerFragment
import com.marbey.saludasuhogar.viewmodel.MedicineViewModel
import kotlinx.android.synthetic.main.activity_add_medicine.*

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
        medicine.dayCharge = day
        medicine.monthCharge = month
        medicine.yearCharge = year
    }

    private val medicine = Medicine()

    private fun onAddMedicineClick(grandParentName: String?, view: View){

        medicine.name = findViewById<EditText>(R.id.medicineNameAdd).text.toString()
        val quantity = findViewById<EditText>(R.id.quantityAdd).text.toString()
        medicine.grandparent = grandParentName
        val dailyDose = findViewById<EditText>(R.id.dailyDoseAdd).text.toString()
        medicine.quantity = quantity.toInt()
        medicine.dailyDose = dailyDose.toDouble()
        val chargeDateAdd = findViewById<EditText>(R.id.ChargeDateAdd).toString()

        fun View.hideKeyboard() {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        if(medicine.name.isNullOrEmpty() || quantity.isNullOrEmpty() || dailyDose.isNullOrEmpty() || chargeDateAdd.isNullOrEmpty()){
            view.hideKeyboard()
            Snackbar.make(view, "Debes ingresar todos los datos de la medicina", Snackbar.LENGTH_LONG).show()
        } else {
            view.hideKeyboard()
            val medicineViewModel = MedicineViewModel()
            medicineViewModel.setMedicineFromFirebase(medicine,medicine.name)
        }

        view.hideKeyboard()
        Snackbar.make(view,"Creando la medicina ${medicine.name}", Snackbar.LENGTH_LONG).show()

        Handler().postDelayed({
            finish()
        }, 1000)

    }

    fun showMessageError(view : View){
        Snackbar.make(view, "La Medicina no ha sido creada", Snackbar.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}