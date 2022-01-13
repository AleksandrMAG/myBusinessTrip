package com.example.mybusinesstrip.screens.accountability

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybusinesstrip.R

class AccountabilityFragment : Fragment() {

    companion object {
        fun newInstance() = AccountabilityFragment()
    }

    private lateinit var viewModel: AccountabilityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.accountability_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountabilityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}