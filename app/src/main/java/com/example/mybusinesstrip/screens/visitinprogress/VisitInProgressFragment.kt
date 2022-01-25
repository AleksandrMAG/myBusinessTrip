package com.example.mybusinesstrip.screens.visitinprogress

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.databinding.VisitInProgressFragmentBinding
import com.example.mybusinesstrip.model.VisitsModel
import com.example.mybusinesstrip.screens.visits.VisitsFragment
import kotlinx.android.synthetic.main.constraint_inner_card.view.*
import kotlinx.android.synthetic.main.constraint_inner_card_contacts.view.*

class VisitInProgressFragment : Fragment() {

    lateinit var binding: VisitInProgressFragmentBinding
    lateinit var currentVisit: VisitsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VisitInProgressFragmentBinding.inflate(layoutInflater, container, false)
        currentVisit = arguments?.getSerializable(VisitsFragment.KEY) as VisitsModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        binding.cardPerson.item_tv_sign.text = currentVisit.signName
        binding.cardPerson.item_tv_person.text = currentVisit.person
        binding.cardPerson.item_tv_locality.text = currentVisit.locality
        binding.cardPerson.item_tv_region.text = currentVisit.region
        binding.cardPerson.item_tv_address.text = currentVisit.address
        binding.cardPerson.item_tv_name.text = currentVisit.contactName

        binding.cardContacs.item_tv_phone1.text = currentVisit.contactPhone
        binding.cardContacs.item_tv_phone2.text = currentVisit.contactPhoneSecond
        binding.cardContacs.item_email.text = currentVisit.contactEmail
        binding.cardContacs.item_site.text = currentVisit.contactSite
        binding.cardContacs.visibility = View.GONE

        binding.tvInprogressDescription.text = currentVisit.infoBefore
        if (currentVisit.infoAfter != "") {
            binding.etInprogressAfter.setText(currentVisit.infoAfter)
        }
        if (currentVisit.infoTodo != "") {
            binding.etInprogressTodo.setText(currentVisit.infoTodo)
        }


    }


}