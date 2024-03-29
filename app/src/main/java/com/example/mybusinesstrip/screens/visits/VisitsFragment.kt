package com.example.mybusinesstrip.screens.visits

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mybusinesstrip.APP
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.adapter.VisitAdapter
import com.example.mybusinesstrip.databinding.VisitsFragmentBinding
import com.example.mybusinesstrip.model.VisitsModel

class VisitsFragment : Fragment() {

    lateinit var binding: VisitsFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: VisitAdapter
    private lateinit var viewModel: VisitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = VisitsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[VisitsViewModel::class.java]
        viewModel.initDatabase()
        recyclerView = binding.rvVisits
        adapter = VisitAdapter()
        recyclerView.adapter = adapter
        viewModel.getAllVisits().observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

        binding.fabAddVisit.setOnClickListener {
            APP.navController.navigate(R.id.action_item_1_to_addVisitFragment)
            APP.binding.bottomNavView.visibility = View.GONE
        }
    }

    companion object {
        const val KEY = "visit"
        fun clickVisit(visitsModel: VisitsModel) {
            val bundle = Bundle()
            bundle.putSerializable(KEY, visitsModel)
            APP.navController.navigate(R.id.action_item_1_to_visitInProgressFragment, bundle)
            APP.binding.bottomNavView.visibility = View.GONE
        }
    }

}