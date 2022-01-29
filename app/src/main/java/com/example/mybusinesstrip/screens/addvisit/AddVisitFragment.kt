package com.example.mybusinesstrip.screens.addvisit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.mybusinesstrip.APP
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.databinding.FragmentAddVisitBinding

class AddVisitFragment : Fragment() {

    lateinit var binding: FragmentAddVisitBinding
    lateinit var viewModel: AddVisitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddVisitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(AddVisitViewModel::class.java)
        binding.etLegal.doAfterTextChanged { viewModel.newVisit.person = it.toString() }
        binding.etLabel.doAfterTextChanged { viewModel.newVisit.signName = it.toString() }
        binding.etAddress.doAfterTextChanged { viewModel.newVisit.address = it.toString() }
        binding.etLocality.doAfterTextChanged { viewModel.newVisit.locality = it.toString() }
        binding.etRegion.doAfterTextChanged { viewModel.newVisit.region = it.toString() }
        binding.etPhone1.doAfterTextChanged { viewModel.newVisit.contactPhone = it.toString() }
        binding.etPhone2.doAfterTextChanged { viewModel.newVisit.contactPhoneSecond = it.toString() }
        binding.etEmail.doAfterTextChanged { viewModel.newVisit.contactEmail = it.toString() }
        binding.etSite.doAfterTextChanged { viewModel.newVisit.contactSite = it.toString() }
        binding.etDiscription.doAfterTextChanged { viewModel.newVisit.infoAfter = it.toString() }
        binding.etTodo.doAfterTextChanged { viewModel.newVisit.infoTodo = it.toString() }

        binding.btnFinishVisit.setOnClickListener {
            viewModel.saveVisit() { }
            APP.navController.navigate(R.id.action_addVisitFragment_to_item_1)
            APP.binding.bottomNavView.visibility = View.VISIBLE
        }
    }

}