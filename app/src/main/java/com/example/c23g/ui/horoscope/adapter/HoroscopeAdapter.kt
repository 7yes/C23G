package com.example.c23g.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.c23g.R
import com.example.c23g.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList()) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopp, parent, false)
        )
    }
    override fun getItemCount(): Int = horoscopeList.size
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val item = horoscopeList[position]
        holder.bind(item)
    }
}