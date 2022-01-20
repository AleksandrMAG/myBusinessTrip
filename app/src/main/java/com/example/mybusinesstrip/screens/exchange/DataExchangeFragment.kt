package com.example.mybusinesstrip.screens.exchange

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybusinesstrip.APP
import com.example.mybusinesstrip.R

class DataExchangeFragment : Fragment() {

    companion object {
        fun newInstance() = DataExchangeFragment()
    }

    private lateinit var viewModel: DataExchangeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_exchange_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataExchangeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}