package com.example.mybusinesstrip.screens.changeinfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.mybusinesstrip.APP
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.databinding.ChangeInfoFragmentBinding
import com.example.mybusinesstrip.model.VisitsModel
import com.example.mybusinesstrip.screens.visits.VisitsFragment

class ChangeInfoFragment : Fragment() {

    private lateinit var viewModel: ChangeInfoViewModel
    private lateinit var binding: ChangeInfoFragmentBinding
    private lateinit var currentVisit: VisitsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChangeInfoFragmentBinding.inflate(inflater, container, false)
        currentVisit = arguments?.getSerializable(VisitsFragment.KEY) as VisitsModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateVisit {  }
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[ChangeInfoViewModel::class.java]
        viewModel.currentVisit = currentVisit

        if (currentVisit.person != "") binding.etLegal.setText(currentVisit.person)
        if (currentVisit.signName != "") binding.etLabel.setText(currentVisit.signName)
        if (currentVisit.address != "") binding.etAddress.setText(currentVisit.address)
        if (currentVisit.locality != "") binding.etLocality.setText(currentVisit.locality)
        if (currentVisit.region != "") binding.etRegion.setText(currentVisit.region)
        if (currentVisit.contactPhone != "") binding.etPhone1.setText(currentVisit.contactPhone)
        if (currentVisit.contactPhoneSecond != "") binding.etPhone2.setText(currentVisit.contactPhoneSecond)
        if (currentVisit.contactEmail != "") binding.etEmail.setText(currentVisit.contactEmail)
        if (currentVisit.contactSite != "") binding.etSite.setText(currentVisit.contactSite)
        if (currentVisit.contactName != "") binding.etName.setText(currentVisit.contactName)

        binding.etLegal.doAfterTextChanged { viewModel.currentVisit.person = it.toString() }
        binding.etLabel.doAfterTextChanged { viewModel.currentVisit.signName = it.toString() }
        binding.etAddress.doAfterTextChanged { viewModel.currentVisit.address = it.toString() }
        binding.etLocality.doAfterTextChanged { viewModel.currentVisit.locality = it.toString() }
        binding.etRegion.doAfterTextChanged { viewModel.currentVisit.region = it.toString() }
        binding.etPhone1.doAfterTextChanged { viewModel.currentVisit.contactPhone = it.toString() }
        binding.etPhone2.doAfterTextChanged { viewModel.currentVisit.contactPhoneSecond = it.toString() }
        binding.etEmail.doAfterTextChanged { viewModel.currentVisit.contactEmail = it.toString() }
        binding.etSite.doAfterTextChanged { viewModel.currentVisit.contactSite = it.toString() }
        binding.etName.doAfterTextChanged { viewModel.currentVisit.contactName = it.toString() }

        binding.btnFinishVisit.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(VisitsFragment.KEY, viewModel.currentVisit)
            APP.navController.navigate(R.id.action_changeInfoFragment_to_visitInProgressFragment, bundle)
        }
    }

}