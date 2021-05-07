package com.marbey.saludasuhogar.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.model.Haven
import com.marbey.saludasuhogar.view.adapter.GrandparentAdapter
import com.marbey.saludasuhogar.view.adapter.GranparentListener
import com.marbey.saludasuhogar.viewmodel.GrandparentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_haven.*
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("DEPRECATION")
class HavenFragment : Fragment(), GranparentListener {

    private lateinit var grandparentAdapter: GrandparentAdapter
    private lateinit var viewModel: GrandparentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_haven, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    override fun onGranparentClicked(granparent: Grandparent, position: Int) {
        val bundle = bundleOf("granparent" to granparent)
        findNavController().navigate(R.id.grandparentDetailFragmentDialog, bundle)
    }
}