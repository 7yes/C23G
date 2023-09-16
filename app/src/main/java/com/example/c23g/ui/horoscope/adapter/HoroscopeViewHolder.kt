package com.example.c23g.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.c23g.databinding.ItemHoroscoppBinding
import com.example.c23g.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscoppBinding.bind(view)
    fun bind(item: HoroscopeInfo, onClickLis: (HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscope.setImageResource(item.img)
        binding.tvTitle.text = context.getString(item.name)
        itemView.setOnClickListener { onClickLis(item) }
    }
}