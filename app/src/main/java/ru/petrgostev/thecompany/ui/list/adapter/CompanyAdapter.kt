package ru.petrgostev.thecompany.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.petrgostev.thecompany.R
import ru.petrgostev.thecompany.data.Company

class CompanyAdapter(private val clickListener: (companyViewItem: Company) -> Unit) :
    ListAdapter<Company, CompanyViewHolder>(CompanyDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.company_view_holder,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { item -> clickListener(item) }
        }
    }
}