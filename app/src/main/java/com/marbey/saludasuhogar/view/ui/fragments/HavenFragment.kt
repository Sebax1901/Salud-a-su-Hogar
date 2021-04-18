package com.marbey.saludasuhogar.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.marbey.saludasuhogar.R
import com.marbey.saludasuhogar.model.Grandparent
import com.marbey.saludasuhogar.view.adapter.GrandparentAdapter
import com.marbey.saludasuhogar.view.adapter.GranparentListener
import com.marbey.saludasuhogar.viewmodel.GrandparentViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HavenFragment : Fragment(), GranparentListener {

    private lateinit var grandparentAdapter: GrandparentAdapter
    private lateinit var viewModel: GrandparentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_haven, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbMain.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.ic_close)
        tbMain.setTitleTextColor(Color.WHITE)
        tbMain.setNavigationOnClickListener {

        }

        val granparent = arguments?.getSerializable("grandparent") as Grandparent
        tbMain.title = granparent.name

        

    }



}