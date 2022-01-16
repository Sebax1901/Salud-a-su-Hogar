package com.marbey.saludasuhogar.view.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.view.adapter.GrandparentAdapter
import com.marbey.saludasuhogar.view.adapter.GrandparentListener
import com.marbey.saludasuhogar.view.ui.activities.AddGrandparentActivity
import com.marbey.saludasuhogar.viewmodel.GrandparentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_grandparents_detail_dialog.*
import kotlinx.android.synthetic.main.fragment_haven.*

@Suppress("DEPRECATION")
class HavenFragment : Fragment(), GrandparentListener {

    private lateinit var grandparentAdapter: GrandparentAdapter
    lateinit var viewModel: GrandparentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_haven, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val haven = arguments?.getSerializable("haven") as Haven
        val havenName = haven.name
        setView()

        addGrandparentIcon.setOnClickListener {
            onPlusGrandparentClicked(havenName, it)
        }

    }

    fun observeViewModel(){
        viewModel.listGrandparent.observe(viewLifecycleOwner, Observer<List<Grandparent>>{ haven ->
            grandparentAdapter.updateData(haven)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if(it != null){
                rlBaseHaven.visibility = View.INVISIBLE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setView()
    }

    override fun onPlusGrandparentClicked(haven: String, view: View) {
        val intent = Intent(activity,AddGrandparentActivity::class.java)
        intent.putExtra("havenName", haven)
        startActivity(intent)
    }

    override fun onGrandparentClicked(grandparent: Grandparent, position: Int) {
        val bundle = bundleOf("grandparent" to grandparent)
        findNavController().navigate(R.id.grandparentDetailFragmentDialog, bundle)
    }

    override fun onGrandparentLongClicked(grandparent: Grandparent, view: View) {
        val bundle = bundleOf("grandparent" to grandparent)
        val grandparentItem = bundle.getSerializable("grandparent") as Grandparent
        val grandparentName = grandparentItem.name

        showAlert(view.context,grandparentName,view)

    }

    private fun showAlert(context: Context, name: String, view: View){
        AlertDialog.Builder(context)
            .setTitle("Elminar Paciente")
            .setMessage("¿Deseas elmininar el paciente $name?")
            .apply {
                setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialog, which ->
                    deleteGrandparent(name, view)
                })
                setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, which ->

                })
            }
            .create().show()
    }

    private fun setView(){
        val haven = arguments?.getSerializable("haven") as Haven
        val havenName = haven.name

        viewModel = ViewModelProviders.of(this).get(GrandparentViewModel::class.java)
        viewModel.refresh(havenName)

        grandparentAdapter = GrandparentAdapter(this)

        rvHavens.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = grandparentAdapter
        }

        observeViewModel()
    }

    private fun deleteGrandparent(name: String, view: View){
        viewModel.deleteGrandparent(name,view)
        setView()
    }

}