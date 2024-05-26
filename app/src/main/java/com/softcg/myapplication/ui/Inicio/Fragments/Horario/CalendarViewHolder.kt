package com.softcg.myapplication.ui.Inicio.Fragments.Horario

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softcg.myapplication.R

import java.time.LocalDate
import kotlin.collections.ArrayList

class CalendarViewHolder(
    itemView: View,
    private val onItemListener: CalendarAdapter.OnItemListener,
    private val days: ArrayList<LocalDate>
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val parentView: View = itemView.findViewById(R.id.parentView)
    val dayOfMonth: TextView = itemView.findViewById(R.id.cellDayText)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        onItemListener.onItemClick(adapterPosition, days[adapterPosition])
    }
}
