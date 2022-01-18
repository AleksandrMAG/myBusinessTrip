package com.example.mybusinesstrip.screens.visits

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.databinding.VisitsFragmentBinding

class VisitsFragment : Fragment() {

    lateinit var binding: VisitsFragmentBinding
    private lateinit var viewModel: VisitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VisitsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(VisitsViewModel::class.java)
        init()
    }

    private fun init() {

    }

}