package ru.petrgostev.thecompany.ui.list.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.petrgostev.thecompany.R
import ru.petrgostev.thecompany.data.Company

class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.image)
    private val name: TextView = itemView.findViewById(R.id.name)

    fun onBind(item: Company) {

        image.load(item.image) {
            crossfade(false)
            error(R.drawable.vector_image_none)
        }

        name.text = item.name
    }
}