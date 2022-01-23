package com.marbey.saludasuhogar.view.ui.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Medicine
import com.marbey.saludasuhogar.view.adapter.MedicineAdapter
import com.marbey.saludasuhogar.view.adapter.MedicineListener
import com.marbey.saludasuhogar.view.ui.activities.AddMedicineActivity
import com.marbey.saludasuhogar.viewmodel.MedicineViewModel
import kotlinx.android.synthetic.main.fragment_grandparents_detail_dialog.*

@Suppress("DEPRECATION")
class GrandparentsDetailDialogFragment : DialogFragment(), MedicineListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    private lateinit var medicineAdapter: MedicineAdapter
    private lateinit var viewModel: MedicineViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grandparents_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarGrandparent.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        toolbarGrandparent.setTitleTextColor(Color.WHITE)
        toolbarGrandparent.setNavigationOnClickListener {
            dismiss()
        }


        val grandparent = arguments?.getSerializable("grandparent") as Grandparent
        var grandparentName = grandparent.name

        setView()

        toolbarGrandparent.title = grandparent.name

        addMedicineIcon.setOnClickListener {
            onPlusMedicineClicked(grandparentName,it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPlusMedicineClicked(grandparentName: String, view: View) {
        val intent = Intent(activity,AddMedicineActivity::class.java)
        intent.putExtra("grandParentName", grandparentName)
        startActivity(intent)
    }

    private fun observeViewModel() {
        viewModel.listMedicine.observe(viewLifecycleOwner, Observer<List<Medicine>>{ medicine ->
                medicineAdapter.updateData(medicine)
        })
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    fun setView(){
        val grandparent = arguments?.getSerializable("grandparent") as Grandparent
        var grandparentName = grandparent.name
        viewModel = ViewModelProviders.of(this).get(MedicineViewModel::class.java)
        viewModel.refresh(grandparentName)

        medicineAdapter = MedicineAdapter(this)

        rvMedicine.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = medicineAdapter
        }

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

}