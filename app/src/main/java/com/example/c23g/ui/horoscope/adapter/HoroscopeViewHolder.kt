package com.example.c23g.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.c23g.databinding.ItemHoroscoppBinding
import com.example.c23g.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscoppBinding.bind(view)
    fun bind(item: HoroscopeInfo, onClickLis: (HoroscopeInfo) -> Unit) {
        val context = binding.tvTitle.context
        binding.ivHoroscope.setImageResource(item.img)
        binding.tvTitle.text = context.getString(item.name)
        // itemView.setOnClickListener { onClickLis(item) }
        startRotationAnimation(binding.ivHoroscope, newLambda = {onClickLis(item)} )
    }
}
private fun startRotationAnimation(view:View, newLambda:()->Unit){
    Toast.makeText(view.context, "entre", Toast.LENGTH_SHORT).show()
    view.animate().apply {

        duration = 1500
        interpolator = LinearInterpolator()
        rotationBy(360f)
        withEndAction { newLambda() }
        start()
    }
}