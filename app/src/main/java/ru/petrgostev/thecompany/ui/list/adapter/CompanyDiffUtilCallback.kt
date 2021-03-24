package ru.petrgostev.thecompany.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.petrgostev.thecompany.data.Company

class CompanyDiffUtilCallback: DiffUtil.ItemCallback<Company>() {
    override fun areItemsTheSame(oldItem: Company, newItem: Company): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Company, newItem: Company): Boolean =
        oldItem == newItem
}