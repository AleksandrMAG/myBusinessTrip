package com.example.mybusinesstrip.screens.visits

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybusinesstrip.R

class VisitsFragment : Fragment() {

    companion object {
        fun newInstance() = VisitsFragment()
    }

    private lateinit var viewModel: VisitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.visits_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VisitsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}