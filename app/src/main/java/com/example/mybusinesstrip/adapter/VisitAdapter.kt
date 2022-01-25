package com.example.mybusinesstrip.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybusinesstrip.R
import com.example.mybusinesstrip.model.VisitsModel
import com.example.mybusinesstrip.screens.visits.VisitsFragment
import kotlinx.android.synthetic.main.constraint_inner_card.view.*

class VisitAdapter : RecyclerView.Adapter<VisitAdapter.VisitViewHolder>() {

    private var listVisits = emptyList<VisitsModel>()

    class VisitViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_visit, parent, false)
        return VisitViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisitViewHolder, position: Int) {
        val visit = listVisits[position]
        holder.itemView.item_tv_locality.text = visit.locality
        holder.itemView.item_tv_region.text = visit.region
        holder.itemView.item_tv_person.text = visit.person
        holder.itemView.item_tv_sign.text = visit.signName
        holder.itemView.item_tv_address.text = visit.address
        holder.itemView.item_tv_name.text = visit.contactName
    }

    override fun getItemCount(): Int {
        return listVisits.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<VisitsModel>) {
        listVisits = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: VisitViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            VisitsFragment.clickVisit(listVisits[holder.adapterPosition])
        }

    }

    override fun onViewDetachedFromWindow(holder: VisitViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}