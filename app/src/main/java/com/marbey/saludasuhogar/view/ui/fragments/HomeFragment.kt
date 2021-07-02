package com.marbey.saludasuhogar.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.view.adapter.HavenAdapter
import com.marbey.saludasuhogar.view.adapter.HavenListener
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

        viewModel = ViewModelProviders.of(this).get(HavenViewModel::class.java)
        viewModel.refresh()

        havenAdapter = HavenAdapter(this)

        rvHome.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = havenAdapter
        }

        observeViewModel()

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

    override fun onHavenClicked(haven: Haven, position: Int) {
        AddHavenIcon.visibility = View.INVISIBLE
        val bundle = bundleOf("haven" to haven)
        findNavController().navigate(R.id.havenFragment, bundle)
    }



}
