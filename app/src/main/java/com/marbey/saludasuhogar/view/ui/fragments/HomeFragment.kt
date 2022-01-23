package com.marbey.saludasuhogar.view.ui.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.view.adapter.HavenAdapter
import com.marbey.saludasuhogar.view.adapter.HavenListener
import com.marbey.saludasuhogar.view.ui.activities.AddHavenActivity
import com.marbey.saludasuhogar.viewmodel.HavenViewModel
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment(), HavenListener {

    private lateinit var havenAdapter: HavenAdapter
    private lateinit var viewModel: HavenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setview()

        addHavenIcon.setOnClickListener {
            onPlusHavenClicked()
        }

    }

    private fun onPlusHavenClicked() {
        val intent = Intent(activity, AddHavenActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        setview()
    }

    private fun observeViewModel(){
        viewModel.listHaven.observe(viewLifecycleOwner, Observer<List<Haven>>{ haven ->
            havenAdapter.updateData(haven)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if(it != null){
                rlBaseHome.visibility = View.INVISIBLE
            }
        })
    }

    private fun setview(){
        viewModel = ViewModelProviders.of(this).get(HavenViewModel::class.java)
        viewModel.refresh()

        havenAdapter = HavenAdapter(this)

        rvHome.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = havenAdapter
        }

        observeViewModel()
    }

    override fun onHavenClicked(haven: Haven, position: Int) {
        addHavenIcon.visibility = View.INVISIBLE
        val bundle = bundleOf("haven" to haven)
        findNavController().navigate(R.id.havenFragment, bundle)
    }

    override fun onHavenLongClicked(haven: Haven, view: View) {
        val bundle = bundleOf("haven" to haven)
        val havenItem = bundle.getSerializable("haven") as Haven
        val havenName = havenItem.name

        showAlert(view.context,havenName,view)

    }

    private fun showAlert(context: Context, name: String, view: View){
        AlertDialog.Builder(context)
            .setTitle("Elminar Hogar")
            .setMessage("¿Deseas elmininar el hogar $name?")
            .apply {
                setPositiveButton(R.string.delete, DialogInterface.OnClickListener { dialog, which ->
                    deleteHaven(name, view)
                })
                setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, which ->

                })
            }
            .create().show()
    }

    private fun deleteHaven(name: String, view: View){
        viewModel.deleteHaven(name, view)
    }



}
